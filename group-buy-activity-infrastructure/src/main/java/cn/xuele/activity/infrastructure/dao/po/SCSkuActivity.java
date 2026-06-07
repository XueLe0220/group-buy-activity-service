package cn.xuele.activity.infrastructure.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 渠道商品活动关系，用于定位商品当前命中的活动。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/06 18:19
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SCSkuActivity {

    /** 自增主键。 */
    private Long id;

    /** 来源标识。 */
    private String source;

    /** 渠道标识。 */
    private String channel;

    /** 关联活动。 */
    private Long activityId;

    /** 关联商品。 */
    private String goodsId;

    /** 记录创建时间。 */
    private LocalDateTime createTime;

    /** 记录更新时间。 */
    private LocalDateTime updateTime;

}
