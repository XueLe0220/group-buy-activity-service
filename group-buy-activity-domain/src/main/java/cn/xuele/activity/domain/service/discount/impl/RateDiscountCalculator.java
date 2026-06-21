package cn.xuele.activity.domain.service.discount.impl;

import cn.xuele.activity.domain.model.valobj.DiscountMarketPlanEnum;
import cn.xuele.activity.domain.model.valobj.GroupBuyActivityDiscountVO;
import cn.xuele.activity.domain.service.discount.AbstractDiscountCalculateService;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 折扣优惠计算策略。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/05 18:01
 */
public class RateDiscountCalculator extends AbstractDiscountCalculateService {

    @Override
    protected BigDecimal doCalculate(BigDecimal originalPrice,
                                     GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {
        BigDecimal discountRate = new BigDecimal(groupBuyDiscount.getMarketExpr().trim());
        BigDecimal payableAmount = originalPrice.multiply(discountRate).setScale(2, RoundingMode.HALF_UP);

        if (payableAmount.compareTo(BigDecimal.ZERO) <= 0) {
            return new BigDecimal("0.01");
        }

        return payableAmount;
    }

    @Override
    public String marketPlan() {
        return DiscountMarketPlanEnum.ZK.getCode();
    }

}
