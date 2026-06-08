package cn.xuele.activity.domain.service.trial.engine;

import cn.xuele.activity.domain.model.entity.MarketProductEntity;
import cn.xuele.activity.domain.model.entity.TrialBalanceEntity;

/**
 * 活动试算规则树入口。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/08 16:37
 */
public interface IActivityTrialRuleEngine {

    /**
     * 执行活动试算规则树。
     *
     * @param request 试算请求
     * @return 试算结果
     */
    TrialBalanceEntity apply(MarketProductEntity request) throws Exception;

}
