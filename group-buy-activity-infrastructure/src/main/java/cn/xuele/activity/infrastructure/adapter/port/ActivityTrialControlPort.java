package cn.xuele.activity.infrastructure.adapter.port;

import cn.xuele.activity.domain.adapter.port.IActivityTrialControlPort;
import org.springframework.stereotype.Component;

/**
 * 活动试算控制端口基础实现。
 * <p>
 * 当前提供保守默认开关，后续接入配置中心时只替换本适配器实现。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/08 17:28
 */
@Component
public class ActivityTrialControlPort implements IActivityTrialControlPort {


    @Override
    public boolean isTrialDowngradeEnabled() {
        return false;
    }

    @Override
    public boolean isUserInTrialGrayRange(String userId) {
        return true;
    }
}
