package cn.xuele.activity.domain.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 商品活动绑定关系。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/05 13:51
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SCSkuActivityVO {

    /** 活动ID。 */
    private Long activityId;

    /** 商品ID。 */
    private String goodsId;

    /** 来源。 */
    private String source;

    /** 渠道。 */
    private String channel;

}
