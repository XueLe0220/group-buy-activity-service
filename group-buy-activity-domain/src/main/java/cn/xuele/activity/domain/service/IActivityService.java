package cn.xuele.activity.domain.service;

import cn.xuele.activity.domain.model.entity.MarketProductEntity;
import cn.xuele.activity.domain.model.entity.TrialBalanceEntity;

/**
 * 活动领域服务接口。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/08 16:46
 */
public interface IActivityService {

    /**
     * 执行活动营销试算。
     *
     * @param request 试算请求
     * @return 试算结果
     */
    TrialBalanceEntity marketTrial(MarketProductEntity request) throws Exception;
}
