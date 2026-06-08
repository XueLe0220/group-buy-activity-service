package cn.xuele.activity.domain.service.discount;

import cn.xuele.activity.domain.model.valobj.GroupBuyActivityDiscountVO;

import java.math.BigDecimal;

/**
 * 优惠计算模板。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/05 17:59
 */
public abstract class AbstractDiscountCalculateService implements IDiscountCalculateService {

    /**
     * 执行通用资格判断，再交给具体策略计算价格。
     *
     * @param originalPrice     原价
     * @param groupBuyDiscount  折扣配置
     * @param isUsable          是否具备优惠资格
     * @return 支付金额
     */
    @Override
    public BigDecimal calculate(BigDecimal originalPrice,
                                GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount, boolean isUsable) {

        if (!isUsable) {
            return originalPrice;
        }

        return doCalculate(originalPrice, groupBuyDiscount);
    }

    /**
     * 执行具体优惠算法。
     *
     * @param originalPrice    原价
     * @param groupBuyDiscount 折扣配置
     * @return 支付金额
     */
    protected abstract BigDecimal doCalculate(BigDecimal originalPrice,
                                              GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount);
}
