package cn.xuele.activity.infrastructure.dao;

import cn.xuele.activity.infrastructure.dao.po.GroupBuyDiscount;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠规则 Mapper，负责活动折扣配置读取。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/06 18:19
 */
@Mapper
public interface IGroupBuyDiscountDao {

    GroupBuyDiscount queryGroupBuyActivityDiscountByDiscountId(String discountId);

}
