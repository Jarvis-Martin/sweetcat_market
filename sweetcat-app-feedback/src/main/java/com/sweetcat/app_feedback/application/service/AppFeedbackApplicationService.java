package com.sweetcat.app_feedback.application.service;

import com.sweetcat.app_feedback.application.command.ProcessFeedbackCommand;
import com.sweetcat.app_feedback.application.command.UploadFeedbackCommand;
import com.sweetcat.app_feedback.application.event.publish.DomainEventPublisher;
import com.sweetcat.app_feedback.domain.feedback.entity.AppFeedback;
import com.sweetcat.app_feedback.domain.feedback.repository.AppFeedbackRepository;
import com.sweetcat.app_feedback.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.app_feedback.infrastructure.service.snowflake_service.SnowFlakeService;
import com.sweetcat.app_feedback.infrastructure.service.timestamp_format_verfiy_service.VerifyTimeStampFormatService;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.domainevent.appfeedback.FeedbackSubmittedEvent;
import com.sweetcat.commons.exception.SaveFileFailException;
import com.sweetcat.commons.util.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/6-19:06
 * @Version: 1.0
 */
@Slf4j
@Service
public class AppFeedbackApplicationService {
    @Value("${upload-file-path}")
    private String uploadFilePath;
    @Value("${static-server-address}")
    private String staticServerAddress;
    @Value("${feedback-pic-path}")
    private String feedbackPicPath;

    private AppFeedbackRepository feedbackRepository;
    private DomainEventPublisher eventPublisher;
    private VerifyIdFormatService verifyIdFormatService;
    private VerifyTimeStampFormatService verifyTimeStampFormatService;
    private SnowFlakeService snowFlakeService;

    @Autowired
    public void setEventPublisher(DomainEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Autowired
    public void setSnowFlakeService(SnowFlakeService snowFlakeService) {
        this.snowFlakeService = snowFlakeService;
    }

    @Autowired
    public void setVerifyTimeStampFormatService(VerifyTimeStampFormatService verifyTimeStampFormatService) {
        this.verifyTimeStampFormatService = verifyTimeStampFormatService;
    }

    @Autowired
    public void setVerifyIdFormatService(VerifyIdFormatService verifyIdFormatService) {
        this.verifyIdFormatService = verifyIdFormatService;
    }

    @Autowired
    public void setFeedbackRepository(AppFeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public AppFeedback findOneByFeedbackId(Long feedbackId) {
        // 检查id
        verifyIdFormatService.verifyIds(feedbackId);
        // 查找
        return feedbackRepository.findOneByFeedbackId(feedbackId);
    }

    public void addAFeedback(UploadFeedbackCommand command) {
        long userId = command.getUserId();
        String[] feedbackPics = command.getFeedbackPics();
        long feedbackTime = command.getFeedbackTime();
        String content = command.getContent();
        // 检查 userId
        verifyIdFormatService.verifyIds(userId);
        // 反馈配图文件名转 json 字符串，以备存入db
        String feedbackPicsStr = JSONUtils.toJson(feedbackPics);
        // 验证 反馈事件格式
        verifyTimeStampFormatService.verifyTimeStamps(feedbackTime);
        // 获得 feedbackId
        long feedbackId = snowFlakeService.snowflakeId();
        // 创建 反馈
        AppFeedback feedback = new AppFeedback(feedbackId);
        inflateAppFeedback(userId, feedbackTime, content, feedbackPicsStr, feedback);
        // 加入db
        feedbackRepository.add(feedback);
        // 构建 FeedbackSubmittedEvent
        FeedbackSubmittedEvent feedbackSubmittedEvent = new FeedbackSubmittedEvent(feedbackId, userId);
        // 触发领域事件 FeedbackSubmittedEvent
        System.out.println("sweetcat-app-feedback: 触发领域事件 feedbackSubmittedEvent 时间为：" + Instant.now().toEpochMilli());
        eventPublisher.syncSend("feedback_topic", feedbackSubmittedEvent);
    }

    private void inflateAppFeedback(long userId, long feedbackTime, String content, String feedbackPicsStr, AppFeedback feedback) {
        feedback.setUserId(userId);
        feedback.setContent(content);
        feedback.setResponseTitle(null);
        feedback.setFeedbackPics(feedbackPicsStr);
        feedback.setStatus(AppFeedback.STATUS_PROCESSING);
        feedback.setCreateTime(feedbackTime);
        feedback.setProcessTime(null);
    }

    /**
     * 保存文件到 nginx
     *
     * @param files files
     * @return 保存的文件的文件名
     */
    private List<String> saveFile(MultipartFile[] files) {
        List<String> fileNames = new ArrayList<>(16);

        Arrays.stream(files).forEach(file -> {
            String randomId = UUID.randomUUID().toString();
            String fileName = file.getOriginalFilename();
            String destFileName = randomId + fileName;
            File dest = new File(uploadFilePath + '/' + destFileName);
            try {
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
                throw new SaveFileFailException(
                        ResponseStatusEnum.SAVEFILEFAILE.getErrorCode(),
                        ResponseStatusEnum.SAVEFILEFAILE.getErrorMessage()
                );
            }
            fileNames.add(staticServerAddress + feedbackPicPath + destFileName);
        });
        // 上传文件的文件名
        return fileNames;
    }

    public void processFeedback(ProcessFeedbackCommand command) {
        AppFeedback feedback = feedbackRepository.findOneByFeedbackId(command.getFeedbackId());
        feedback.setProcessorId(command.getProcessorId());
        feedback.setResponseContent(command.getResponseContent());
        feedback.setResponseTitle(command.getResponseTitle());
        feedback.setProcessTime(command.getProcessTime());
        feedbackRepository.save(feedback);
    }
}
