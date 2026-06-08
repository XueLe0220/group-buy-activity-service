package cn.xuele.activity.domain.service.trial.node;

import cn.xuele.activity.domain.adapter.repository.IActivityRepository;
import cn.xuele.activity.domain.model.entity.MarketProductEntity;
import cn.xuele.activity.domain.model.entity.TrialBalanceEntity;
import cn.xuele.activity.domain.model.valobj.GroupBuyActivityDiscountVO;
import cn.xuele.activity.domain.model.valobj.SCSkuActivityVO;
import cn.xuele.activity.domain.model.valobj.SkuVO;
import cn.xuele.activity.domain.service.trial.engine.AbstractActivityTrialSupport;
import cn.xuele.activity.domain.service.trial.engine.ActivityTrialContext;
import cn.xuele.common.design.framework.tree.StrategyHandler;
import cn.xuele.common.types.enums.ResponseCode;
import cn.xuele.common.types.exception.AppException;

/**
 * 活动试算数据加载节点。
 * <p>
 * 负责通过活动仓储端口加载商品活动绑定关系、有效活动优惠配置和商品价格快照。
 * 本节点只依赖领域仓储端口，不直接访问 DAO、Mapper、PO 或缓存实现。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/08 14:30
 */
public class DataLoadNode extends AbstractActivityTrialSupport {

    /** 活动仓储端口。 */
    private final IActivityRepository activityRepository;

    /** 下一个节点：试算控制节点。 */
    private final TrialControlNode trialControlNode;

    public DataLoadNode(IActivityRepository activityRepository, TrialControlNode trialControlNode) {
        this.activityRepository = activityRepository;
        this.trialControlNode = trialControlNode;
    }

    @Override
    protected TrialBalanceEntity doApply(MarketProductEntity requestParameter,
                                         ActivityTrialContext dynamicContext) throws Exception {
        // 根据渠道、来源、商品查活动信息
        SCSkuActivityVO skuActivity = activityRepository.querySkuActivity(
                requestParameter.getGoodsId(), requestParameter.getSource(), requestParameter.getChannel());
        if (skuActivity == null || skuActivity.getActivityId() == null) {
            throw new AppException(ResponseCode.NO_ACTIVITY_MARKET_CONFIG);
        }

        // 根据活动ID查活动折扣聚合信息
        GroupBuyActivityDiscountVO activityDiscount = activityRepository.queryValidActivityDiscount(
                skuActivity.getActivityId());
        // 根据渠道、来源、商品ID查商品信息
        SkuVO sku = activityRepository.querySku(
                requestParameter.getGoodsId(), requestParameter.getSource(), requestParameter.getChannel());
        if (activityDiscount == null || activityDiscount.getGroupBuyDiscount() == null || sku == null) {
            throw new AppException(ResponseCode.NO_ACTIVITY_MARKET_CONFIG);
        }

        // 写入动态上下文
        dynamicContext.setSkuActivity(skuActivity);
        dynamicContext.setActivityDiscount(activityDiscount);
        dynamicContext.setSku(sku);

        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<MarketProductEntity, ActivityTrialContext, TrialBalanceEntity> get(
            MarketProductEntity requestParameter, ActivityTrialContext dynamicContext) {
        return trialControlNode;
    }
}
