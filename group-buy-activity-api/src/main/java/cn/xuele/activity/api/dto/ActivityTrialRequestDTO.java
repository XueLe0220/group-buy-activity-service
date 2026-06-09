package cn.xuele.activity.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 活动试算请求 DTO。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/09 12:12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityTrialRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 用户ID。 */
    private String userId;

    /** 商品ID。 */
    private String goodsId;

    /** 来源。 */
    private String source;

    /** 渠道。 */
    private String channel;

}
