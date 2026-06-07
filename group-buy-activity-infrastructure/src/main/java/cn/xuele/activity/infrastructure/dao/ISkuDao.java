package cn.xuele.activity.infrastructure.dao;

import cn.xuele.activity.infrastructure.dao.po.Sku;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品快照 Mapper，负责试算所需商品基础信息读取。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/06 18:19
 */
@Mapper
public interface ISkuDao {

    Sku querySku(Sku request);

}
