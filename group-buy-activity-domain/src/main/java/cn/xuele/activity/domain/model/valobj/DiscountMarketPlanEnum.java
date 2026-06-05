package cn.xuele.activity.domain.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 营销计划类型。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/05 17:49
 */
@Getter
@AllArgsConstructor
public enum DiscountMarketPlanEnum {

    /** 直减。 */
    ZJ("ZJ", "直减"),

    /** 满减。 */
    MJ("MJ", "满减"),

    /** N元购。 */
    N("N", "N元购"),

    /** 折扣。 */
    ZK("ZK", "折扣");

    /** 计划编码。 */
    private final String code;

    /** 计划描述。 */
    private final String info;

}
