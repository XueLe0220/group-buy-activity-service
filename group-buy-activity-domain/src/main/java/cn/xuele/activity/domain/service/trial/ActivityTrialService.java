package cn.xuele.activity.domain.service.trial;

import cn.xuele.activity.domain.model.entity.MarketProductEntity;
import cn.xuele.activity.domain.model.entity.TrialBalanceEntity;
import cn.xuele.activity.domain.service.trial.engine.IActivityTrialRuleEngine;

/**
 * 活动试算领域服务。
 * <p>
 * 对外收敛活动试算能力，内部委托规则树入口完成具体流程。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/08 16:45
 */
public class ActivityTrialService implements IActivityTrialService {

    /** 活动试算规则树入口。 */
    private final IActivityTrialRuleEngine activityTrialRuleEngine;

    public ActivityTrialService(IActivityTrialRuleEngine activityTrialRuleEngine) {
        this.activityTrialRuleEngine = activityTrialRuleEngine;
    }

    @Override
    public TrialBalanceEntity marketTrial(MarketProductEntity request) throws Exception {
        return activityTrialRuleEngine.apply(request);
    }

}
