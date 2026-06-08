package cn.xuele.activity.domain.service.trial.node;

import cn.xuele.activity.domain.adapter.port.ITagQueryPort;
import cn.xuele.activity.domain.model.entity.MarketProductEntity;
import cn.xuele.activity.domain.model.entity.TrialBalanceEntity;
import cn.xuele.activity.domain.model.valobj.DiscountTypeEnum;
import cn.xuele.activity.domain.model.valobj.GroupBuyActivityDiscountVO;
import cn.xuele.activity.domain.service.trial.engine.AbstractActivityTrialSupport;
import cn.xuele.activity.domain.service.trial.engine.ActivityTrialContext;
import cn.xuele.common.design.framework.tree.StrategyHandler;
import cn.xuele.common.types.enums.ResponseCode;
import cn.xuele.common.types.exception.AppException;

import static cn.xuele.common.types.common.StringUtils.isBlank;

/**
 * 活动试算人群标签节点。
 * <p>
 * 负责根据活动标签门禁规则判断用户是否具备活动可见、可参与和专享优惠资格。
 * 本节点只依赖标签查询端口，不直接访问 tag-service 的数据表、Redis bitmap 或基础设施实现。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/08 14:30
 */
public class TagNode extends AbstractActivityTrialSupport {

    /** 标签查询端口。 */
    private final ITagQueryPort tagQueryPort;

    /** 下一个节点：营销计算节点。 */
    private final MarketNode marketNode;

    public TagNode(ITagQueryPort tagQueryPort, MarketNode marketNode) {
        this.tagQueryPort = tagQueryPort;
        this.marketNode = marketNode;
    }

    @Override
    protected TrialBalanceEntity doApply(MarketProductEntity requestParameter,
                                         ActivityTrialContext dynamicContext) throws Exception {
        GroupBuyActivityDiscountVO activityDiscount = dynamicContext.getActivityDiscount();
        String activityTagId = activityDiscount.getTagId();
        boolean defaultVisible = activityDiscount.isVisible();
        boolean defaultEnable = activityDiscount.isEnable();
        Boolean userInActivityTag = null;

        if (defaultVisible && defaultEnable) {
            // 活动默认可用
            dynamicContext.setVisible(true);
            dynamicContext.setEnable(true);
        } else if (isBlank(activityTagId)) {
            // 活动有限制 但未配置相关标签，则按照限制拦截
            dynamicContext.setVisible(defaultVisible);
            dynamicContext.setEnable(defaultEnable);
        } else {
            // 匹配标签结果
            userInActivityTag = tagQueryPort.matchUserTag(requestParameter.getUserId(), activityTagId);
            dynamicContext.setVisible(defaultVisible || userInActivityTag);
            dynamicContext.setEnable(defaultEnable || userInActivityTag);
        }

        dynamicContext.setDiscountEligible(resolveDiscountEligible(
                requestParameter, activityDiscount, dynamicContext, userInActivityTag));

        return router(requestParameter, dynamicContext);
    }

    /**
     * 计算用户是否具备本次优惠资格。
     * <p>
     * 基础优惠只要求活动可见且可参与；专享优惠还要求用户命中折扣自身配置的人群标签。
     */
    private boolean resolveDiscountEligible(MarketProductEntity requestParameter,
                                            GroupBuyActivityDiscountVO activityDiscount,
                                            ActivityTrialContext dynamicContext,
                                            Boolean userInActivityTag) {
        if (!Boolean.TRUE.equals(dynamicContext.getVisible()) || !Boolean.TRUE.equals(dynamicContext.getEnable())) {
            return false;
        }

        GroupBuyActivityDiscountVO.GroupBuyDiscount discount = activityDiscount.getGroupBuyDiscount();
        if (discount == null) {
            throw new AppException(ResponseCode.NO_ACTIVITY_MARKET_CONFIG);
        }

        if (!DiscountTypeEnum.TAG.equals(discount.getDiscountType())) {
            return true;
        }

        String discountTagId = discount.getTagId();
        if (isBlank(discountTagId)) {
            return false;
        }

        if (discountTagId.equals(activityDiscount.getTagId()) && userInActivityTag != null) {
            return userInActivityTag;
        }

        return tagQueryPort.matchUserTag(requestParameter.getUserId(), discountTagId);
    }

    @Override
    public StrategyHandler<MarketProductEntity, ActivityTrialContext, TrialBalanceEntity> get(
            MarketProductEntity requestParameter, ActivityTrialContext dynamicContext) {
        return marketNode;
    }
}
