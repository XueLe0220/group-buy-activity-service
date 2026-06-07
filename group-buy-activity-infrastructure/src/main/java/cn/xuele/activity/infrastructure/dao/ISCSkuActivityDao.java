package cn.xuele.activity.infrastructure.dao;

import cn.xuele.activity.infrastructure.dao.po.SCSkuActivity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品活动关系 Mapper，负责按渠道定位活动。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/06 18:19
 */
@Mapper
public interface ISCSkuActivityDao {

    SCSkuActivity querySkuActivity(SCSkuActivity request);

}
