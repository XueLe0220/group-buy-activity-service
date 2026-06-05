package cn.xuele.activity.domain.adapter.repository;

import cn.xuele.activity.domain.model.valobj.GroupBuyActivityDiscountVO;
import cn.xuele.activity.domain.model.valobj.SCSkuActivityVO;
import cn.xuele.activity.domain.model.valobj.SkuVO;

/**
 * 活动领域仓储端口。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/05 14:00
 */
public interface IActivityRepository {

    /**
     * 查询有效活动及折扣配置。
     *
     * @param activityId 活动ID
     * @return 活动折扣配置
     */
    GroupBuyActivityDiscountVO queryValidActivityDiscount(Long activityId);

    /**
     * 查询商品价格快照。
     *
     * @param goodsId 商品ID
     * @return 商品价格快照
     */
    SkuVO querySkuByGoodsId(String goodsId);

    /**
     * 查询商品与活动的绑定关系。
     *
     * @param goodsId 商品ID
     * @param source  来源
     * @param channel 渠道
     * @return 商品活动绑定关系
     */
    SCSkuActivityVO querySkuActivity(String goodsId, String source, String channel);

}
