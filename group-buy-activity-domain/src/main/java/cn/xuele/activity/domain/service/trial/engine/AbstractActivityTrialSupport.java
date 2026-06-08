package cn.xuele.activity.domain.service.trial.engine;

import cn.xuele.activity.domain.model.entity.MarketProductEntity;
import cn.xuele.activity.domain.model.entity.TrialBalanceEntity;
import cn.xuele.common.design.framework.tree.AbstractStrategyRouter;

/**
 * 活动试算规则树通用支撑基类。
 * <p>
 * 该类只负责固定活动试算规则树的泛型类型，避免每个节点重复声明
 * MarketProductEntity、ActivityTrialContext、TrialBalanceEntity 三个类型。
 * 具体仓储、标签、灰度、优惠策略等端口由各业务节点按需持有，避免父类承载过多职责。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/08 14:03
 */
public abstract class AbstractActivityTrialSupport extends AbstractStrategyRouter<MarketProductEntity,
        ActivityTrialContext, TrialBalanceEntity> {
}
