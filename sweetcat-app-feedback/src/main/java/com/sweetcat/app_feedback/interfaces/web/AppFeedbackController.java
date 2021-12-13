package com.sweetcat.app_feedback.interfaces.web;

import com.sweetcat.app_feedback.application.command.UploadFeedbackCommand;
import com.sweetcat.app_feedback.interfaces.facade.AppFeedbackFacade;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/6-19:25
 * @Version: 1.0
 */
@RestController
@RequestMapping("/feedback")
public class AppFeedbackController {
    private AppFeedbackFacade feedbackFacade;

    @Autowired
    public void setFeedbackFacade(AppFeedbackFacade feedbackFacade) {
        this.feedbackFacade = feedbackFacade;
    }

    @PostMapping("/upload")
    public ResponseDTO addAFeedback(UploadFeedbackCommand command) {
        feedbackFacade.addAFeedback(command);

        String tip = "反馈成功，客服人员将及时为您处理，请稍等";
        return response(tip, "{}");
    }

    /**
     * 通用的放回 ResponseDTO
     *
     * @param tip  用户提示信息
     * @param data 数据部分
     * @return ResponseDTO
     */
    private ResponseDTO response(String tip, Object data) {
        return new ResponseDTO(
                ResponseStatusEnum.SUCCESS.getErrorCode(),
                ResponseStatusEnum.SUCCESS.getErrorMessage(),
                tip,
                data
        );
    }
}
