package cn.xuele.activity.domain.service.discount.impl;

import cn.xuele.activity.domain.model.valobj.DiscountMarketPlanEnum;
import cn.xuele.activity.domain.model.valobj.GroupBuyActivityDiscountVO;
import cn.xuele.activity.domain.service.discount.AbstractDiscountCalculateService;

import java.math.BigDecimal;

/**
 * 直减优惠计算策略。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/05 18:00
 */
public class DirectReductionDiscountCalculator extends AbstractDiscountCalculateService {

    @Override
    protected BigDecimal doCalculate(BigDecimal originalPrice,
                                     GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {
        // 1. 获取直减金额
        BigDecimal deductionAmount = new BigDecimal(groupBuyDiscount.getMarketExpr().trim());

        // 2. 计算：原价 - 扣减金额
        BigDecimal payPrice = originalPrice.subtract(deductionAmount);

        // 3. 兜底检验
        if (payPrice.compareTo(BigDecimal.ZERO) <= 0) {
            return new BigDecimal("0.01");
        }
        return payPrice;
    }


    @Override
    public String marketPlan() {
        return DiscountMarketPlanEnum.ZJ.getCode();
    }

}
