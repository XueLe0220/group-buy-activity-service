package cn.xuele.activity.app.config;

import cn.xuele.activity.domain.adapter.port.IActivityTrialControlPort;
import cn.xuele.activity.domain.adapter.port.ITagQueryPort;
import cn.xuele.activity.domain.adapter.repository.IActivityRepository;
import cn.xuele.activity.domain.service.discount.IDiscountCalculateService;
import cn.xuele.activity.domain.service.discount.impl.DirectReductionDiscountCalculator;
import cn.xuele.activity.domain.service.discount.impl.FixedPriceDiscountCalculator;
import cn.xuele.activity.domain.service.discount.impl.FullReductionDiscountCalculator;
import cn.xuele.activity.domain.service.discount.impl.RateDiscountCalculator;
import cn.xuele.activity.domain.service.ActivityService;
import cn.xuele.activity.domain.service.IActivityService;
import cn.xuele.activity.domain.service.trial.engine.ActivityTrialRuleEngine;
import cn.xuele.activity.domain.service.trial.engine.IActivityTrialRuleEngine;
import cn.xuele.activity.domain.service.trial.node.DataLoadNode;
import cn.xuele.activity.domain.service.trial.node.EndNode;
import cn.xuele.activity.domain.service.trial.node.MarketNode;
import cn.xuele.activity.domain.service.trial.node.RootNode;
import cn.xuele.activity.domain.service.trial.node.TagNode;
import cn.xuele.activity.domain.service.trial.node.TrialControlNode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 活动领域服务装配类。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/09 10:36
 */
@Configuration
public class DomainServiceConfig {
    @Bean
    public IActivityService activityService(IActivityTrialRuleEngine activityTrialRuleEngine){return new ActivityService(activityTrialRuleEngine);}
    @Bean
    public IDiscountCalculateService directReductionDiscountCalculator() {return new DirectReductionDiscountCalculator();}
    @Bean
    public IDiscountCalculateService fullReductionDiscountCalculator() {
        return new FullReductionDiscountCalculator();
    }
    @Bean
    public IDiscountCalculateService fixedPriceDiscountCalculator() {
        return new FixedPriceDiscountCalculator();
    }
    @Bean
    public IDiscountCalculateService rateDiscountCalculator() {
        return new RateDiscountCalculator();
    }

    @Bean
    public EndNode endNode() {
        return new EndNode();
    }

    @Bean
    public MarketNode marketNode(List<IDiscountCalculateService> discountCalculateServices,
                                 EndNode endNode) {
        Map<String, IDiscountCalculateService> discountCalculateServiceMap =
                buildDiscountCalculateServiceMap(discountCalculateServices);
        return new MarketNode(discountCalculateServiceMap, endNode);
    }

    @Bean
    public TagNode tagNode(ITagQueryPort tagQueryPort, MarketNode marketNode) {
        return new TagNode(tagQueryPort, marketNode);
    }

    @Bean
    public TrialControlNode trialControlNode(IActivityTrialControlPort activityTrialControlPort,
                                             TagNode tagNode) {
        return new TrialControlNode(activityTrialControlPort, tagNode);
    }

    @Bean
    public DataLoadNode dataLoadNode(IActivityRepository activityRepository,
                                     TrialControlNode trialControlNode) {
        return new DataLoadNode(activityRepository, trialControlNode);
    }

    @Bean
    public RootNode rootNode(DataLoadNode dataLoadNode) {
        return new RootNode(dataLoadNode);
    }

    @Bean
    public IActivityTrialRuleEngine activityTrialRuleEngine(RootNode rootNode) {
        return new ActivityTrialRuleEngine(rootNode);
    }


    private Map<String, IDiscountCalculateService> buildDiscountCalculateServiceMap(
            List<IDiscountCalculateService> discountCalculateServices) {
        Map<String, IDiscountCalculateService> discountCalculateServiceMap = new HashMap<>();
        for (IDiscountCalculateService discountCalculateService : discountCalculateServices) {
            IDiscountCalculateService old = discountCalculateServiceMap.put(
                    discountCalculateService.marketPlan(), discountCalculateService);
            if (old != null) {
                throw new IllegalStateException("Duplicate discount calculator: "
                        + discountCalculateService.marketPlan());
            }
        }
        return discountCalculateServiceMap;
    }
}
