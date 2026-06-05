package cn.xuele.activity.domain.service.trial;

import cn.xuele.activity.domain.model.entity.MarketProductEntity;
import cn.xuele.activity.domain.model.entity.TrialBalanceEntity;

/**
 * 活动试算规则引擎。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/05 17:35
 */
public interface IActivityTrialRuleEngine {

    /**
     * 执行活动试算规则链。
     *
     * @param marketProductEntity 试算请求
     * @return 试算结果
     */
    TrialBalanceEntity process(MarketProductEntity marketProductEntity);

}
