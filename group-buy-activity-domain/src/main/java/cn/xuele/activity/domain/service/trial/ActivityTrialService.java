package cn.xuele.activity.domain.service.trial;

import cn.xuele.activity.domain.model.entity.MarketProductEntity;
import cn.xuele.activity.domain.model.entity.TrialBalanceEntity;

/**
 * 活动试算领域服务实现。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/05 17:34
 */
public class ActivityTrialService  implements IActivityTrialService {

    /** 活动试算规则引擎。 */
    private final IActivityTrialRuleEngine activityTrialRuleEngine;

    public ActivityTrialService(IActivityTrialRuleEngine activityTrialRuleEngine) {
        this.activityTrialRuleEngine = activityTrialRuleEngine;
    }

    @Override
    public TrialBalanceEntity trial(MarketProductEntity marketProductEntity) {
        return activityTrialRuleEngine.process(marketProductEntity);
    }
}
