package cn.xuele.activity.domain.service.discount.impl;

import cn.xuele.activity.domain.model.valobj.DiscountMarketPlanEnum;
import cn.xuele.activity.domain.model.valobj.GroupBuyActivityDiscountVO;
import cn.xuele.activity.domain.service.discount.AbstractDiscountCalculateService;

import java.math.BigDecimal;

/**
 * 满减优惠计算策略。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/05 18:00
 */
public class FullReductionDiscountCalculator extends AbstractDiscountCalculateService {

    @Override
    protected BigDecimal doCalculate(BigDecimal originalPrice,
                                     GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {
        String[] marketExpr = groupBuyDiscount.getMarketExpr().split(",");
        if (marketExpr.length != 2) {
            throw new IllegalArgumentException("Full reduction marketExpr must be threshold,discount");
        }

        BigDecimal thresholdAmount = new BigDecimal(marketExpr[0].trim());
        BigDecimal discountAmount = new BigDecimal(marketExpr[1].trim());

        if (originalPrice.compareTo(thresholdAmount) < 0) {
            return originalPrice;
        }

        BigDecimal payPrice = originalPrice.subtract(discountAmount);
        if (payPrice.compareTo(BigDecimal.ZERO) <= 0) {
            return new BigDecimal("0.01");
        }

        return payPrice;
    }

    @Override
    public String marketPlan() {
        return DiscountMarketPlanEnum.MJ.getCode();
    }

}
