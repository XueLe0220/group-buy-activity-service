package cn.xuele.activity.domain.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 优惠类型。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/05 13:56
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum DiscountTypeEnum {

    /** 基础优惠。 */
    BASE(0, "基础优惠"),

    /** 人群标签优惠。 */
    TAG(1, "人群标签");

    /** 类型编码。 */
    private Integer code;

    /** 类型描述。 */
    private String info;

    public static DiscountTypeEnum valueOfCode(Integer code) {
        for (DiscountTypeEnum discountType : values()) {
            if (discountType.getCode().equals(code)) {
                return discountType;
            }
        }
        throw new IllegalArgumentException("Unsupported discount type code: " + code);
    }

}
