package cn.xuele.activity.infrastructure.dao;

import cn.xuele.activity.infrastructure.dao.po.GroupBuyActivity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 活动主表 Mapper，后续只承接 activity-service 自有表查询。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/06 18:19
 */
@Mapper
public interface IGroupBuyActivityDao {

    GroupBuyActivity queryValidGroupBuyActivity(GroupBuyActivity request);

}
