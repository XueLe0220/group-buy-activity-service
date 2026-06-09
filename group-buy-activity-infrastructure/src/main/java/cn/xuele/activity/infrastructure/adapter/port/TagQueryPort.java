package cn.xuele.activity.infrastructure.adapter.port;

import cn.xuele.activity.domain.adapter.port.ITagQueryPort;
import cn.xuele.common.types.enums.ResponseCode;
import cn.xuele.common.types.exception.AppException;
import cn.xuele.common.types.response.Response;
import cn.xuele.tag.api.ITagQueryService;
import cn.xuele.tag.api.dto.TagQueryRequestDTO;
import cn.xuele.tag.api.dto.TagQueryResponseDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import static cn.xuele.common.types.common.StringUtils.isBlank;

/**
 * 人群标签查询端口 Dubbo 适配器。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/09 10:26
 */
@Component
public class TagQueryPort implements ITagQueryPort {

    @DubboReference(check = false)
    private ITagQueryService tagQueryService;

    @Override
    public boolean matchUserTag(String userId, String tagId) {
        if (isBlank(userId) || isBlank(tagId)) {
            throw new AppException(ResponseCode.ILLEGAL_PARAMETER);
        }

        try {
            Response<TagQueryResponseDTO> response = tagQueryService.matchCrowdTag(TagQueryRequestDTO.builder()
                    .userId(userId)
                    .tagId(tagId)
                    .build());

            if (response == null) {
                throw new AppException(ResponseCode.UN_ERROR.getCode(), "标签服务返回空响应");
            }

            if (!response.isSuccess()) {
                throw new AppException(response.getCode(), "标签服务查询失败：" + response.getInfo());
            }

            TagQueryResponseDTO data = response.getData();
            if (data == null) {
                throw new AppException(ResponseCode.UN_ERROR.getCode(), "标签服务返回空标签结果");
            }

            return data.isMatched();
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException(ResponseCode.UN_ERROR.getCode(), "调用标签服务查询用户标签失败", e);
        }
    }
}
