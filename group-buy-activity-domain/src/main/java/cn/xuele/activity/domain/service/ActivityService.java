package cn.xuele.activity.domain.service;

import cn.xuele.activity.domain.model.entity.MarketProductEntity;
import cn.xuele.activity.domain.model.entity.TrialBalanceEntity;
import cn.xuele.activity.domain.service.trial.engine.IActivityTrialRuleEngine;

/**
 * 活动领域服务。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/08 16:45
 */
public class ActivityService implements IActivityService {

    /** 活动试算规则树入口。 */
    private final IActivityTrialRuleEngine activityTrialRuleEngine;

    public ActivityService(IActivityTrialRuleEngine activityTrialRuleEngine) {
        this.activityTrialRuleEngine = activityTrialRuleEngine;
    }

    @Override
    public TrialBalanceEntity marketTrial(MarketProductEntity request) throws Exception {
        return activityTrialRuleEngine.apply(request);
    }

}
