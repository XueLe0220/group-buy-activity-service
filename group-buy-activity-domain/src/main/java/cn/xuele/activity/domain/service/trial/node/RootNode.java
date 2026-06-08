package cn.xuele.activity.domain.service.trial.node;

import cn.xuele.activity.domain.model.entity.MarketProductEntity;
import cn.xuele.activity.domain.model.entity.TrialBalanceEntity;
import cn.xuele.activity.domain.service.trial.engine.AbstractActivityTrialSupport;
import cn.xuele.activity.domain.service.trial.engine.ActivityTrialContext;
import cn.xuele.common.design.framework.tree.StrategyHandler;
import cn.xuele.common.types.enums.ResponseCode;
import cn.xuele.common.types.exception.AppException;

import static cn.xuele.common.types.common.StringUtils.isBlank;

/**
 * 活动试算规则树根节点。
 * <p>
 * 负责校验试算请求的基础入参，例如 userId、goodsId、source、channel，
 * 确保无效请求不会继续进入活动加载、标签判断和优惠计算节点。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/08 11:53
 */
public class RootNode extends AbstractActivityTrialSupport {

    /** 下一个节点：数据加载节点。 */
    private final DataLoadNode dataLoadNode;

    public RootNode(DataLoadNode dataLoadNode) {
        this.dataLoadNode = dataLoadNode;
    }

    @Override
    protected TrialBalanceEntity doApply(MarketProductEntity requestParameter,
                                         ActivityTrialContext dynamicContext) throws Exception {
        // 参数非空校验
        if (requestParameter == null
                || isBlank(requestParameter.getUserId())
                || isBlank(requestParameter.getGoodsId())
                || isBlank(requestParameter.getSource())
                || isBlank(requestParameter.getChannel())) {
            throw new AppException(ResponseCode.ILLEGAL_PARAMETER);
        }

        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<MarketProductEntity, ActivityTrialContext, TrialBalanceEntity> get(
            MarketProductEntity requestParameter, ActivityTrialContext dynamicContext) {
        return dataLoadNode;
    }
}
