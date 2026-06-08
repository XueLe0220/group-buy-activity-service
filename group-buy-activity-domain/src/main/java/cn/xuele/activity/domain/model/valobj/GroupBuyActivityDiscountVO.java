package cn.xuele.activity.domain.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

import static cn.xuele.common.types.common.StringUtils.isBlank;

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

    /**
     * 活动ID。
     */
    private Long activityId;

    /**
     * 活动名称。
     */
    private String activityName;

    /**
     * 来源。
     */
    private String source;

    /**
     * 渠道。
     */
    private String channel;

    /**
     * 商品ID。
     */
    private String goodsId;

    /**
     * 拼团方式。
     */
    private Integer groupType;

    /**
     * 每人限购次数。
     */
    private Integer takeLimitCount;

    /**
     * 成团目标人数。
     */
    private Integer target;

    /**
     * 拼团有效时长，单位：分钟。
     */
    private Integer validTime;

    /**
     * 活动状态。
     */
    private Integer status;

    /**
     * 活动开始时间。
     */
    private LocalDateTime startTime;

    /**
     * 活动结束时间。
     */
    private LocalDateTime endTime;

    /**
     * 人群标签ID。
     */
    private String tagId;

    /**
     * 标签作用范围，例 "1,2"
     * 第一位为可见性标识
     * 第二位为可用性标识
     * 空：不设限；
     *
     */
    private String tagScope;


    /**
     * 判断活动是否默认可见。
     * <p>
     * tagScope 为空表示不做人群门禁，活动默认可见。
     * tagScope 第一位等于 1 表示可见性受限，需要后续标签节点查询用户是否命中白名单。
     *
     * @return true 表示默认可见，false 表示需要标签命中后才可见
     */
    public boolean isVisible() {
        if (isBlank(tagScope)) {
            return TagScopeEnumVO.VISIBLE.getAllow();
        }
        String[] split = tagScope.split(",");

        // 校验可见性标识
        if (split.length > 0 && Objects.equals(split[0], "1")) {
            return TagScopeEnumVO.VISIBLE.getRefuse();
        }

        return TagScopeEnumVO.VISIBLE.getAllow();

    }

    /**
     * 判断活动是否默认可参与。
     * <p>
     * tagScope 为空表示不做人群门禁，活动默认可参与。
     * tagScope 第二位等于 2 表示参与资格受限，需要后续标签节点查询用户是否命中白名单。
     *
     * @return true 表示默认可参与，false 表示需要标签命中后才可参与
     */
    public boolean isEnable() {
        if (isBlank(tagScope)) {
            return TagScopeEnumVO.ENABLE.getAllow();
        }
        String[] split = tagScope.split(",");

        // 校验可用性标识
        if (split.length > 1 && Objects.equals(split[1], "2")) {
            return TagScopeEnumVO.ENABLE.getRefuse();
        }
        return TagScopeEnumVO.ENABLE.getAllow();
    }


    /**
     * 折扣配置。
     */
    private GroupBuyDiscount groupBuyDiscount;

    /**
     * 折扣配置。
     */
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GroupBuyDiscount {
        /**
         * 折扣名称。
         */
        private String discountName;

        /**
         * 折扣描述。
         */
        private String discountDesc;

        /**
         * 折扣类型。
         */
        private DiscountTypeEnum discountType;

        /**
         * 营销策略标识。
         */
        private String marketPlan;

        /**
         * 营销表达式。
         */
        private String marketExpr;

        /**
         * 专享人群标签。
         */
        private String tagId;
    }

}
