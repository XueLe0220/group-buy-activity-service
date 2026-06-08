package cn.xuele.activity.domain.service.trial.engine;

import cn.xuele.activity.domain.model.entity.MarketProductEntity;
import cn.xuele.activity.domain.model.entity.TrialBalanceEntity;
import cn.xuele.activity.domain.service.trial.node.RootNode;

/**
 * 活动试算规则树引擎。
 * <p>
 * 只负责创建规则树上下文并调用根节点，不承载具体业务判断。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/08 16:38
 */
public class ActivityTrialRuleEngine implements IActivityTrialRuleEngine {

    /** 规则树根节点。 */
    private final RootNode rootNode;

    public ActivityTrialRuleEngine(RootNode rootNode) {
        this.rootNode = rootNode;
    }

    @Override
    public TrialBalanceEntity apply(MarketProductEntity request) throws Exception {
        return rootNode.apply(request, new ActivityTrialContext());
    }
}
