package cn.xuele.activity.domain.service.trial.node;

import cn.xuele.activity.domain.model.entity.MarketProductEntity;
import cn.xuele.activity.domain.model.entity.TrialBalanceEntity;
import cn.xuele.activity.domain.model.valobj.GroupBuyActivityDiscountVO;
import cn.xuele.activity.domain.model.valobj.SkuVO;
import cn.xuele.activity.domain.service.trial.engine.AbstractActivityTrialSupport;
import cn.xuele.activity.domain.service.trial.engine.ActivityTrialContext;
import cn.xuele.common.design.framework.tree.StrategyHandler;

/**
 * 活动试算结束节点。
 * <p>
 * 负责根据规则树上下文组装最终 TrialBalanceEntity。
 * 本节点不访问外部端口，不执行额外业务判断，只承担试算结果出站前的领域结果组装职责。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/08 14:30
 */
public class EndNode extends AbstractActivityTrialSupport {

    @Override
    protected TrialBalanceEntity doApply(MarketProductEntity requestParameter,
                                         ActivityTrialContext dynamicContext) {
        SkuVO sku = dynamicContext.getSku();
        GroupBuyActivityDiscountVO activityDiscount = dynamicContext.getActivityDiscount();

        return TrialBalanceEntity.builder()
                .activityId(activityDiscount.getActivityId())
                .activityName(activityDiscount.getActivityName())
                .goodsId(sku.getGoodsId())
                .goodsName(sku.getGoodsName())
                .originalPrice(sku.getOriginalPrice())
                .deductionPrice(dynamicContext.getDeductionPrice())
                .payPrice(dynamicContext.getPayPrice())
                .targetCount(activityDiscount.getTarget())
                .validTime(activityDiscount.getValidTime())
                .startTime(activityDiscount.getStartTime())
                .endTime(activityDiscount.getEndTime())
                .isVisible(dynamicContext.getVisible())
                .isEnable(dynamicContext.getEnable())
                .groupBuyActivityDiscountVO(activityDiscount)
                .build();
    }

    @Override
    public StrategyHandler<MarketProductEntity, ActivityTrialContext, TrialBalanceEntity> get(
            MarketProductEntity requestParameter, ActivityTrialContext dynamicContext) {
        return getDefaultStrategyHandler();
    }
}
