package cn.xuele.activity.domain.adapter.port;

/**
 * 人群标签查询端口。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/05 15:11
 */
public interface ITagQueryPort {

    /**
     * 判断用户是否命中指定标签。
     *
     * @param userId 用户ID
     * @param tagId  标签ID
     * @return true 表示命中
     */
    boolean matchUserTag(String userId, String tagId);

}
