package cn.xuele.activity.domain.service.discount.impl;

import cn.xuele.activity.domain.model.valobj.DiscountMarketPlanEnum;
import cn.xuele.activity.domain.model.valobj.GroupBuyActivityDiscountVO;
import cn.xuele.activity.domain.service.discount.AbstractDiscountCalculateService;

import java.math.BigDecimal;

/**
 * N元购优惠计算策略。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/05 18:01
 */
public class FixedPriceDiscountCalculator extends AbstractDiscountCalculateService {

    @Override
    protected BigDecimal doCalculate(BigDecimal originalPrice,
                                     GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {
        BigDecimal payPrice = new BigDecimal(groupBuyDiscount.getMarketExpr().trim());

        if (payPrice.compareTo(BigDecimal.ZERO) <= 0) {
            return new BigDecimal("0.01");
        }

        return payPrice;
    }

    @Override
    public String marketPlan() {
        return DiscountMarketPlanEnum.N.getCode();
    }

}
