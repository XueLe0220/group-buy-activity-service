package cn.xuele.activity.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 活动试算响应 DTO。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/09 12:13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityTrialResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 活动ID。 */
    private Long activityId;

    /** 活动名称。 */
    private String activityName;

    /** 商品ID。 */
    private String goodsId;

    /** 商品名称。 */
    private String goodsName;

    /** 商品原价。 */
    private BigDecimal originalPrice;

    /** 优惠金额。 */
    private BigDecimal deductionPrice;

    /** 试算支付价。 */
    private BigDecimal payPrice;

    /** 成团目标人数。 */
    private Integer targetCount;

    /** 拼团有效时间，单位分钟。 */
    private Integer validTime;

    /** 活动开始时间。 */
    private LocalDateTime startTime;

    /** 活动结束时间。 */
    private LocalDateTime endTime;

    /** 是否对当前用户可见。 */
    private Boolean visible;

    /** 是否允许当前用户参与。 */
    private Boolean enable;

}
