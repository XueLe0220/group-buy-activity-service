package cn.xuele.activity.domain.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 标签控制范围。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/05 13:58
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum TagScopeEnumVO {
    /** 控制活动可见性。 */
    VISIBLE(true, false, "是否可看见拼团"),

    /** 控制活动参与资格。 */
    ENABLE(true, false, "是否可参与拼团"),
    ;

    /** 默认放行值。 */
    private Boolean allow;

    /** 默认拦截值。 */
    private Boolean refuse;

    /** 描述。 */
    private String desc;
}
