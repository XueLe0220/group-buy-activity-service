package cn.xuele.activity.infrastructure.adapter.repository;

import cn.xuele.activity.domain.adapter.repository.IActivityRepository;
import cn.xuele.activity.domain.model.valobj.DiscountTypeEnum;
import cn.xuele.activity.domain.model.valobj.GroupBuyActivityDiscountVO;
import cn.xuele.activity.domain.model.valobj.SCSkuActivityVO;
import cn.xuele.activity.domain.model.valobj.SkuVO;
import cn.xuele.activity.infrastructure.dao.IGroupBuyActivityDao;
import cn.xuele.activity.infrastructure.dao.IGroupBuyDiscountDao;
import cn.xuele.activity.infrastructure.dao.ISCSkuActivityDao;
import cn.xuele.activity.infrastructure.dao.ISkuDao;
import cn.xuele.activity.infrastructure.dao.po.GroupBuyActivity;
import cn.xuele.activity.infrastructure.dao.po.GroupBuyDiscount;
import cn.xuele.activity.infrastructure.dao.po.SCSkuActivity;
import cn.xuele.activity.infrastructure.dao.po.Sku;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 活动仓储适配器，聚合活动、优惠和商品数据后交给领域层。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/06 18:19
 */
@Repository
@RequiredArgsConstructor
public class ActivityRepository implements IActivityRepository {

    private final IGroupBuyActivityDao groupBuyActivityDao;
    private final IGroupBuyDiscountDao groupBuyDiscountDao;
    private final ISCSkuActivityDao scSkuActivityDao;
    private final ISkuDao skuDao;

    /**
     * 查询有效活动及折扣配置。
     *
     * @param activityId 活动ID
     * @return 活动折扣配置
     */
    @Override
    public GroupBuyActivityDiscountVO queryValidActivityDiscount(Long activityId) {

        GroupBuyActivity groupBuyActivity = groupBuyActivityDao.queryValidGroupBuyActivity(
                GroupBuyActivity.builder()
                        .activityId(activityId)
                        .build());
        if (null == groupBuyActivity || null == groupBuyActivity.getDiscountId()) {
            return null;
        }

        GroupBuyDiscount groupBuyDiscount = groupBuyDiscountDao.queryGroupBuyActivityDiscountByDiscountId(
                groupBuyActivity.getDiscountId());
        if (null == groupBuyDiscount) {
            return null;
        }

        return GroupBuyActivityDiscountVO.builder()
                .activityId(groupBuyActivity.getActivityId())
                .activityName(groupBuyActivity.getActivityName())
                .groupType(groupBuyActivity.getGroupType())
                .takeLimitCount(groupBuyActivity.getTakeLimitCount())
                .target(groupBuyActivity.getTarget())
                .validTime(groupBuyActivity.getValidTime())
                .status(groupBuyActivity.getStatus())
                .startTime(groupBuyActivity.getStartTime())
                .endTime(groupBuyActivity.getEndTime())
                .tagId(groupBuyActivity.getTagId())
                .tagScope(groupBuyActivity.getTagScope())
                .groupBuyDiscount(GroupBuyActivityDiscountVO.GroupBuyDiscount.builder()
                        .discountName(groupBuyDiscount.getDiscountName())
                        .discountDesc(groupBuyDiscount.getDiscountDesc())
                        .discountType(DiscountTypeEnum.valueOfCode(groupBuyDiscount.getDiscountType()))
                        .marketPlan(groupBuyDiscount.getMarketPlan())
                        .marketExpr(groupBuyDiscount.getMarketExpr())
                        .tagId(groupBuyDiscount.getTagId())
                        .build())
                .build();
    }

    /**
     * 查询商品价格快照。
     *
     * @param goodsId 商品ID
     * @param source  来源
     * @param channel 渠道
     * @return 商品价格快照
     */
    @Override
    public SkuVO querySku(String goodsId, String source, String channel) {
        Sku sku = skuDao.querySku(Sku.builder()
                .goodsId(goodsId)
                .source(source)
                .channel(channel)
                .build());
        if (null == sku) {
            return null;
        }

        return SkuVO.builder()
                .goodsId(sku.getGoodsId())
                .goodsName(sku.getGoodsName())
                .originalPrice(sku.getOriginalPrice())
                .build();
    }

    /**
     * 查询商品与活动的绑定关系。
     *
     * @param goodsId 商品ID
     * @param source  来源
     * @param channel 渠道
     * @return 商品活动绑定关系
     */
    @Override
    public SCSkuActivityVO querySkuActivity(String goodsId, String source, String channel) {
        SCSkuActivity scSkuActivity = scSkuActivityDao.querySkuActivity(SCSkuActivity.builder()
                .goodsId(goodsId)
                .source(source)
                .channel(channel)
                .build());
        if (null == scSkuActivity) {
            return null;
        }

        return SCSkuActivityVO.builder()
                .activityId(scSkuActivity.getActivityId())
                .goodsId(scSkuActivity.getGoodsId())
                .source(scSkuActivity.getSource())
                .channel(scSkuActivity.getChannel())
                .build();
    }
}
