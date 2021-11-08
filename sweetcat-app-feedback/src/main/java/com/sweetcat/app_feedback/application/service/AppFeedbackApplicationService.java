package com.sweetcat.app_feedback.application.service;

import com.google.gson.Gson;
import com.sweetcat.app_feedback.domain.feedback.entity.AppFeedback;
import com.sweetcat.app_feedback.domain.feedback.repository.AppFeedbackRepository;
import com.sweetcat.app_feedback.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.app_feedback.infrastructure.service.snowflake_service.SnowFlakeService;
import com.sweetcat.app_feedback.infrastructure.service.timestamp_format_verfiy_service.VerifyTimeStampFormatService;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.SaveFileFailException;
import com.sweetcat.commons.util.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
@Service
public class AppFeedbackApplicationService {
    @Value("${upload-file-path}")
    private String uploadFilePath;
    @Value("${static-server-address}")
    private String staticServerAddress;
    @Value("${feedback-pic-path}")
    private String feedbackPicPath;


    private AppFeedbackRepository feedbackRepository;

    private VerifyIdFormatService verifyIdFormatService;
    private VerifyTimeStampFormatService verifyTimeStampFormatService;
    private SnowFlakeService snowFlakeService;

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

    public void addAFeedback(Long userId, String content, String[] feedbackPics, Long feedbackTime) {
        // 检查 userId
        verifyIdFormatService.verifyIds(userId);
        // 反馈配图文件名转 json 字符串，以备存入db
        String feedbackPicsStr = JSONUtils.toJson(feedbackPics);
        // 验证 反馈事件格式
        verifyTimeStampFormatService.verifyTimeStamps(feedbackTime);
        // 获得 feedbackId
        long feedbackId = snowFlakeService.snowflakeId();
        // 创建 反馈
        AppFeedback appFeedback = new AppFeedback(feedbackId, userId, content, feedbackPicsStr, AppFeedback.STATUS_PROCESSING, feedbackTime);
        // 加入db
        feedbackRepository.add(appFeedback);
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

}
