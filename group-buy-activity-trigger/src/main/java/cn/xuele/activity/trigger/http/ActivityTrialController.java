package cn.xuele.activity.trigger.http;

import cn.xuele.activity.api.dto.ActivityTrialRequestDTO;
import cn.xuele.activity.api.dto.ActivityTrialResponseDTO;
import cn.xuele.activity.domain.model.entity.MarketProductEntity;
import cn.xuele.activity.domain.model.entity.TrialBalanceEntity;
import cn.xuele.activity.domain.service.IActivityService;
import cn.xuele.common.types.enums.ResponseCode;
import cn.xuele.common.types.exception.AppException;
import cn.xuele.common.types.response.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.xuele.common.types.common.StringUtils.isBlank;

/**
 * 活动试算 HTTP 控制器。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/09 16:50
 */
@RestController
@RequestMapping("/api/activity")
public class ActivityTrialController {

    private final IActivityService activityService;

    public ActivityTrialController(IActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping("/trial")
    public Response<ActivityTrialResponseDTO> trial(@RequestBody ActivityTrialRequestDTO request) {
        if (request == null) {
            return Response.failure(ResponseCode.ILLEGAL_PARAMETER);
        }

        String channel = request.getChannel();
        String source = request.getSource();
        String goodsId = request.getGoodsId();
        String userId = request.getUserId();

        if (isBlank(channel) || isBlank(source) || isBlank(goodsId) || isBlank(userId)) {
            return Response.failure(ResponseCode.ILLEGAL_PARAMETER);
        }

        try {
            MarketProductEntity requestEntity = MarketProductEntity.builder()
                    .userId(userId)
                    .goodsId(goodsId)
                    .activityId(request.getActivityId())
                    .source(source)
                    .channel(channel)
                    .build();

            TrialBalanceEntity trialBalanceEntity = activityService.marketTrial(requestEntity);

            ActivityTrialResponseDTO response = ActivityTrialResponseDTO.builder()
                    .activityId(trialBalanceEntity.getActivityId())
                    .activityName(trialBalanceEntity.getActivityName())
                    .goodsId(trialBalanceEntity.getGoodsId())
                    .goodsName(trialBalanceEntity.getGoodsName())
                    .originalPrice(trialBalanceEntity.getOriginalPrice())
                    .deductionPrice(trialBalanceEntity.getDeductionPrice())
                    .payableAmount(trialBalanceEntity.getPayableAmount())
                    .targetCount(trialBalanceEntity.getTargetCount())
                    .validTime(trialBalanceEntity.getValidTime())
                    .takeLimitCount(trialBalanceEntity.getTakeLimitCount())
                    .startTime(trialBalanceEntity.getStartTime())
                    .endTime(trialBalanceEntity.getEndTime())
                    .visible(trialBalanceEntity.getIsVisible())
                    .enable(trialBalanceEntity.getIsEnable())
                    .build();

            return Response.success(response);
        } catch (AppException e) {
            return Response.failure(e.getCode(), e.getInfo());
        } catch (Exception e) {
            return Response.failure(ResponseCode.UN_ERROR);
        }
    }

}
