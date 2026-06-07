package cn.xuele.activity.infrastructure.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 优惠规则配置，marketExpr 由 marketPlan 对应策略解析。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/06 18:19
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupBuyDiscount {

    /** 自增主键。 */
    private Long id;

    /** 优惠业务主键。 */
    private String discountId;

    /** 优惠名称。 */
    private String discountName;

    /** 优惠简述。 */
    private String discountDesc;

    /** 优惠适用类型。 */
    private Integer discountType;

    /** 优惠算法标识。 */
    private String marketPlan;

    /** 优惠算法表达式。 */
    private String marketExpr;

    /** 专享人群标签。 */
    private String tagId;

    /** 记录创建时间。 */
    private LocalDateTime createTime;

    /** 记录更新时间。 */
    private LocalDateTime updateTime;

}
