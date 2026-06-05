package cn.xuele.activity.domain.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 活动折扣配置。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/05 13:53
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupBuyActivityDiscountVO {

    /** 活动ID。 */
    private Long activityId;

    /** 活动名称。 */
    private String activityName;

    /** 来源。 */
    private String source;

    /** 渠道。 */
    private String channel;

    /** 商品ID。 */
    private String goodsId;

    /** 拼团方式。 */
    private Integer groupType;

    /** 每人限购次数。 */
    private Integer takeLimitCount;

    /** 成团目标人数。 */
    private Integer target;

    /** 拼团有效时长，单位：分钟。 */
    private Integer validTime;

    /** 活动状态。 */
    private Integer status;

    /** 活动开始时间。 */
    private LocalDateTime startTime;

    /** 活动结束时间。 */
    private LocalDateTime endTime;

    /** 人群标签ID。 */
    private String tagId;

    /** 标签作用范围。 */
    private String tagScope;

    /** 折扣配置。 */
    private GroupBuyDiscount groupBuyDiscount;

    /**
     * 折扣配置。
     */
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GroupBuyDiscount {
        /** 折扣名称。 */
        private String discountName;

        /** 折扣描述。 */
        private String discountDesc;

        /** 折扣类型。 */
        private DiscountTypeEnum discountType;

        /** 营销策略标识。 */
        private String marketPlan;

        /** 营销表达式。 */
        private String marketExpr;

        /** 专享人群标签。 */
        private String tagId;
    }

}
