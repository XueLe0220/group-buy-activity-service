package cn.xuele.activity.domain.service.trial;

import cn.xuele.activity.domain.adapter.port.ITagQueryPort;
import cn.xuele.activity.domain.adapter.repository.IActivityRepository;
import cn.xuele.activity.domain.model.entity.MarketProductEntity;
import cn.xuele.activity.domain.model.entity.TrialBalanceEntity;
import cn.xuele.activity.domain.service.discount.IDiscountCalculateService;
import lombok.RequiredArgsConstructor;

import java.util.Map;

/**
 * TODO: 类描述
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/07 23:42
 */
@RequiredArgsConstructor
public class ActivityTrialRuleEngine implements IActivityTrialRuleEngine{

    private final IActivityRepository activityRepository;
    private final ITagQueryPort tagQueryPort;
    private final Map<String, IDiscountCalculateService> discountCalculateServiceMap;

    @Override
    public TrialBalanceEntity process(MarketProductEntity marketProductEntity) {


        return null;
    }
}
