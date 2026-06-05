package cn.xuele.activity.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 营销试算请求。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/05 13:46
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarketProductEntity {

    /** 活动ID。 */
    private Long activityId;

    /** 用户ID。 */
    private String userId;

    /** 商品ID。 */
    private String goodsId;

    /** 来源。 */
    private String source;

    /** 渠道。 */
    private String channel;

}
