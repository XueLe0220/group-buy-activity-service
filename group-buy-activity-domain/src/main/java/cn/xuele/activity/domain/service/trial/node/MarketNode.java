package cn.xuele.activity.domain.service.trial.node;

import cn.xuele.activity.domain.model.entity.MarketProductEntity;
import cn.xuele.activity.domain.model.entity.TrialBalanceEntity;
import cn.xuele.activity.domain.model.valobj.GroupBuyActivityDiscountVO;
import cn.xuele.activity.domain.model.valobj.SkuVO;
import cn.xuele.activity.domain.service.discount.IDiscountCalculateService;
import cn.xuele.activity.domain.service.trial.engine.AbstractActivityTrialSupport;
import cn.xuele.activity.domain.service.trial.engine.ActivityTrialContext;
import cn.xuele.common.design.framework.tree.StrategyHandler;
import cn.xuele.common.types.enums.ResponseCode;
import cn.xuele.common.types.exception.AppException;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 活动试算营销计算节点。
 * <p>
 * 负责根据活动优惠配置路由具体优惠计算策略，计算优惠金额和最终支付金额。
 * 本节点只负责策略选择和价格试算，不承载活动数据加载、标签校验或结果组装职责。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/08 14:30
 */
public class MarketNode extends AbstractActivityTrialSupport {

    /** 优惠计算策略集合，key 为营销计划编码。 */
    private final Map<String, IDiscountCalculateService> discountCalculateServiceMap;

    /** 下一个节点：结束节点。 */
    private final EndNode endNode;

    public MarketNode(Map<String, IDiscountCalculateService> discountCalculateServiceMap, EndNode endNode) {
        this.discountCalculateServiceMap = discountCalculateServiceMap;
        this.endNode = endNode;
    }

    @Override
    protected TrialBalanceEntity doApply(MarketProductEntity requestParameter,
                                         ActivityTrialContext dynamicContext) throws Exception {
        GroupBuyActivityDiscountVO activityDiscount = dynamicContext.getActivityDiscount();
        GroupBuyActivityDiscountVO.GroupBuyDiscount discount = activityDiscount.getGroupBuyDiscount();
        SkuVO sku = dynamicContext.getSku();

        IDiscountCalculateService discountCalculateService = discountCalculateServiceMap.get(discount.getMarketPlan());
        if (discountCalculateService == null) {
            throw new AppException(ResponseCode.NO_DISCOUNT_CALCULATOR);
        }

        BigDecimal payableAmount = discountCalculateService.calculate(
                sku.getOriginalPrice(), discount, Boolean.TRUE.equals(dynamicContext.getDiscountEligible()));
        BigDecimal deductionPrice = sku.getOriginalPrice().subtract(payableAmount);

        dynamicContext.setPayableAmount(payableAmount);
        dynamicContext.setDeductionPrice(deductionPrice);

        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<MarketProductEntity, ActivityTrialContext, TrialBalanceEntity> get(
            MarketProductEntity requestParameter, ActivityTrialContext dynamicContext) {
        return endNode;
    }
}
