package cn.xuele.activity.infrastructure.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品价格快照，试算链路只读取原价和展示名称。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/06 18:19
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sku {

    /** 自增主键。 */
    private Long id;

    /** 来源标识。 */
    private String source;

    /** 渠道标识。 */
    private String channel;

    /** 商品业务主键。 */
    private String goodsId;

    /** 商品展示名称。 */
    private String goodsName;

    /** 商品原价。 */
    private BigDecimal originalPrice;

    /** 记录创建时间。 */
    private LocalDateTime createTime;

    /** 记录更新时间。 */
    private LocalDateTime updateTime;

}
