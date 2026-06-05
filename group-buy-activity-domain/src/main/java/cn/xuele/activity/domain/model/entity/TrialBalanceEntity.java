package cn.xuele.activity.domain.model.entity;

import cn.xuele.activity.domain.model.valobj.GroupBuyActivityDiscountVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 营销试算结果。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/05 13:48
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrialBalanceEntity {

    /** 商品ID。 */
    private String goodsId;

    /** 商品名称。 */
    private String goodsName;

    /** 原价。 */
    private BigDecimal originalPrice;

    /** 优惠金额。 */
    private BigDecimal deductionPrice;

    /** 支付价。 */
    private BigDecimal payPrice;

    /** 成团目标人数。 */
    private Integer targetCount;

    /** 活动开始时间。 */
    private LocalDateTime startTime;

    /** 活动结束时间。 */
    private LocalDateTime endTime;

    /** 是否可见。 */
    private Boolean isVisible;

    /** 是否可参与。 */
    private Boolean isEnable;

    /** 活动折扣配置。 */
    private GroupBuyActivityDiscountVO groupBuyActivityDiscountVO;

}
