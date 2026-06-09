package cn.xuele.activity.api;

import cn.xuele.activity.api.dto.ActivityTrialRequestDTO;
import cn.xuele.activity.api.dto.ActivityTrialResponseDTO;
import cn.xuele.common.types.response.Response;

/**
 * 活动试算 RPC 契约。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/09 12:12
 */
public interface IActivityTrialService {

    /**
     * 执行活动试算。
     *
     * @param request 活动试算请求
     * @return 活动试算响应
     */
    Response<ActivityTrialResponseDTO> trial(ActivityTrialRequestDTO request);
}
