package com.sweetcat.customerservice.interfaces.web;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.customerservice.application.command.AddFeedbackCommand;
import com.sweetcat.customerservice.application.command.ProcessFeedbackCommand;
import com.sweetcat.customerservice.interfaces.facade.FeedbackFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/13-20:56
 * @version: 1.0
 */
@RestController
@RequestMapping("/customer_service/feedback")
public class FeedbackController {
    private FeedbackFacade feedbackFacade;

    @Autowired
    public void setFeedbackFacade(FeedbackFacade feedbackFacade) {
        this.feedbackFacade = feedbackFacade;
    }

    @PostMapping("/")
    public ResponseDTO addOne(AddFeedbackCommand command) {
        feedbackFacade.addOne(command);
        return response("添加成功", "{}");
    }

    @PostMapping("/process/{record_id}")
    public ResponseDTO processFeedback(ProcessFeedbackCommand command) {
        feedbackFacade.processFeedback(command);
        return response("处理成功", "{}");
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
