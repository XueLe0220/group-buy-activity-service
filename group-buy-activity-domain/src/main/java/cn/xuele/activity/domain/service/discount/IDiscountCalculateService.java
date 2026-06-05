package cn.xuele.activity.domain.service.discount;

import cn.xuele.activity.domain.model.valobj.GroupBuyActivityDiscountVO;

import java.math.BigDecimal;

/**
 * 优惠计算策略。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/05 17:37
 */
public interface IDiscountCalculateService {

    /**
     * 当前策略支持的营销计划标识。
     *
     * @return 营销计划编码
     */
    String marketPlan();

    /**
     * 计算优惠后的支付金额。
     *
     * @param originalPrice    原价
     * @param discount         折扣配置
     * @param discountEligible 是否具备优惠资格
     * @return 支付金额
     */
    BigDecimal calculate(BigDecimal originalPrice,
                         GroupBuyActivityDiscountVO.GroupBuyDiscount discount,
                         boolean discountEligible);

}
