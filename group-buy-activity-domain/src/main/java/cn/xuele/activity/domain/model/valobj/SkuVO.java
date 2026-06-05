package cn.xuele.activity.domain.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 商品价格快照。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/05 13:50
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkuVO {

    /** 商品ID。 */
    private String goodsId;

    /** 商品名称。 */
    private String goodsName;

    /** 原价。 */
    private BigDecimal originalPrice;

}
