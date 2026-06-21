package cn.xuele.activity.domain.service.trial.engine;

import cn.xuele.activity.domain.model.valobj.GroupBuyActivityDiscountVO;
import cn.xuele.activity.domain.model.valobj.SCSkuActivityVO;
import cn.xuele.activity.domain.model.valobj.SkuVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 活动试算规则树上下文。
 * <p>
 * 用于承载规则树节点流转过程中产生的中间数据，例如活动配置、商品快照、标签命中结果和优惠计算结果。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/08 11:50
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityTrialContext {

    /** 商品活动绑定关系。 */
    private SCSkuActivityVO skuActivity;

    /** 活动折扣配置。 */
    private GroupBuyActivityDiscountVO activityDiscount;

    /** 商品价格快照。 */
    private SkuVO sku;

    /** 用户是否可见活动。 */
    private Boolean visible;

    /** 用户是否可参与活动。 */
    private Boolean enable;

    /** 用户是否具备当前优惠资格。 */
    private Boolean discountEligible;

    /** 优惠金额。 */
    private BigDecimal deductionPrice;

    /** 最终应付金额。 */
    private BigDecimal payableAmount;
}
