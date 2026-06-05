package cn.xuele.activity.domain.adapter.port;

/**
 * 活动试算控制端口。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/05 15:09
 */
public interface IActivityTrialControlPort {

    /**
     * 是否开启试算降级。
     *
     * @return true 表示降级
     */
    boolean isTrialDowngradeEnabled();

    /**
     * 判断用户是否进入试算灰度范围。
     *
     * @param userId 用户ID
     * @return true 表示进入灰度范围
     */
    boolean isUserInTrialGrayRange(String userId);

}
