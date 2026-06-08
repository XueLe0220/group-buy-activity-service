package cn.xuele.activity.domain.service.trial.node;

import cn.xuele.activity.domain.adapter.port.IActivityTrialControlPort;
import cn.xuele.activity.domain.model.entity.MarketProductEntity;
import cn.xuele.activity.domain.model.entity.TrialBalanceEntity;
import cn.xuele.activity.domain.service.trial.engine.AbstractActivityTrialSupport;
import cn.xuele.activity.domain.service.trial.engine.ActivityTrialContext;
import cn.xuele.common.design.framework.tree.StrategyHandler;
import cn.xuele.common.types.enums.ResponseCode;
import cn.xuele.common.types.exception.AppException;

/**
 * 活动试算控制节点。
 * <p>
 * 负责承接活动试算链路中的降级、灰度和切量控制。
 * 本节点只依赖活动试算控制端口，不直接感知配置中心、开关存储或具体治理组件。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/08 14:30
 */
public class TrialControlNode extends AbstractActivityTrialSupport {

    /** 活动试算控制端口。 */
    private final IActivityTrialControlPort activityTrialControlPort;

    /** 下一个节点：人群标签节点。 */
    private final TagNode tagNode;

    public TrialControlNode(IActivityTrialControlPort activityTrialControlPort, TagNode tagNode) {
        this.activityTrialControlPort = activityTrialControlPort;
        this.tagNode = tagNode;
    }

    @Override
    protected TrialBalanceEntity doApply(MarketProductEntity requestParameter,
                                         ActivityTrialContext dynamicContext) throws Exception {

        // 检查是否服务降级
        if (activityTrialControlPort.isTrialDowngradeEnabled()) {
            throw new AppException(ResponseCode.ACTIVITY_TRIAL_DOWNGRADE);
        }

        // 用户是否在灰度发布范围内
        if (!activityTrialControlPort.isUserInTrialGrayRange(requestParameter.getUserId())) {
            throw new AppException(ResponseCode.ACTIVITY_TRIAL_GRAY_RANGE_BLOCKED);
        }

        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<MarketProductEntity, ActivityTrialContext, TrialBalanceEntity> get(
            MarketProductEntity requestParameter, ActivityTrialContext dynamicContext) {
        return tagNode;
    }
}
