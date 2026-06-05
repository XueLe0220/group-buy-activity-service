package cn.xuele.activity.domain.service.trial;

import cn.xuele.activity.domain.model.entity.MarketProductEntity;
import cn.xuele.activity.domain.model.entity.TrialBalanceEntity;

/**
 * 活动试算领域服务。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/05 17:27
 */
public interface IActivityTrialService {

    /**
     * 计算用户在指定商品和渠道下的活动试算结果。
     *
     * @param marketProductEntity 试算请求
     * @return 试算结果
     */
    TrialBalanceEntity trial(MarketProductEntity marketProductEntity);

}
