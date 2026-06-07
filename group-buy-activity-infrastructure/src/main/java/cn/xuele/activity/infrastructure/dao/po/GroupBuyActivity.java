package cn.xuele.activity.infrastructure.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 活动主配置，承载试算所需的活动基础规则。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/06 18:19
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupBuyActivity {

    /** 自增主键。 */
    private Long id;

    /** 活动业务主键。 */
    private Long activityId;

    /** 活动展示名称。 */
    private String activityName;

    /** 关联优惠配置。 */
    private String discountId;

    /** 拼团成团方式。 */
    private Integer groupType;

    /** 单人参与上限。 */
    private Integer takeLimitCount;

    /** 成团目标人数。 */
    private Integer target;

    /** 拼团有效分钟数。 */
    private Integer validTime;

    /** 活动生命周期状态。 */
    private Integer status;

    /** 活动开始时间。 */
    private LocalDateTime startTime;

    /** 活动结束时间。 */
    private LocalDateTime endTime;

    /** 活动人群标签。 */
    private String tagId;

    /** 标签作用范围。 */
    private String tagScope;

    /** 记录创建时间。 */
    private LocalDateTime createTime;

    /** 记录更新时间。 */
    private LocalDateTime updateTime;

}
