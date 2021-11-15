package com.sweetcat.customerservice.application.service;

import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;
import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.domainevent.appcustomerservice.FeedbackProcessedByCustomerServiceEvent;
import com.sweetcat.commons.exception.AppFeedbackNotExistedException;
import com.sweetcat.commons.exception.CustomerServiceStaffNotExistedException;
import com.sweetcat.commons.exception.UserNotExistedException;
import com.sweetcat.customerservice.application.command.AddFeedbackCommand;
import com.sweetcat.customerservice.application.command.ProcessFeedbackCommand;
import com.sweetcat.customerservice.application.event.publish.DomainEventPublisher;
import com.sweetcat.customerservice.application.rpc.AppFeedbackRpc;
import com.sweetcat.customerservice.application.rpc.UserInfoRpc;
import com.sweetcat.customerservice.domain.feedback.entity.Feedback;
import com.sweetcat.customerservice.domain.feedback.entity.Informer;
import com.sweetcat.customerservice.domain.feedback.entity.Receiver;
import com.sweetcat.customerservice.domain.feedback.repository.FeedbackRepository;
import com.sweetcat.customerservice.domain.staff.entity.CustomerServiceStaff;
import com.sweetcat.customerservice.domain.staff.repository.CustomerServiceStaffRepository;
import com.sweetcat.customerservice.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.customerservice.infrastructure.service.snowflake_service.SnowFlakeService;
import com.sweetcat.customerservice.infrastructure.service.timestamp_format_verfiy_service.VerifyTimeStampFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/13-14:07
 * @version: 1.0
 */
@Service
public class FeedbackApplicationService {
    private FeedbackRepository feedbackRepository;
    private CustomerServiceStaffRepository staffRepository;
    private DomainEventPublisher eventPublisher;
    private VerifyIdFormatService verifyIdFormatService;
    private VerifyTimeStampFormatService verifyTimeStampFormatService;
    private SnowFlakeService snowFlakeService;
    private AppFeedbackRpc feedbackRpc;
    private UserInfoRpc userInfoRpc;

    @Autowired
    public void setUserInfoRpc(UserInfoRpc userInfoRpc) {
        this.userInfoRpc = userInfoRpc;
    }

    @Autowired
    public void setEventPublisher(DomainEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Autowired
    public void setStaffRepository(CustomerServiceStaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Autowired
    public void setSnowFlakeService(SnowFlakeService snowFlakeService) {
        this.snowFlakeService = snowFlakeService;
    }

    @Autowired
    public void setFeedbackRpc(AppFeedbackRpc feedbackRpc) {
        this.feedbackRpc = feedbackRpc;
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
    public void setFeedbackRepository(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    /**
     * 添加
     * @param command command
     */
    public void addOne(AddFeedbackCommand command) {
        long feedbackId = command.getFeedbackId();
        long receiverId = command.getReceiverId();
        // 检查id
        verifyIdFormatService.verifyIds(receiverId, feedbackId);
//        // rpc 查询 feedback 是否存在
//        AppFeedbackRpcDTO feedbackRpcDTO = feedbackRpc.findByFeedbackId(feedbackId);
//        // 反馈记录不存在
//        if (feedbackRpcDTO == null) {
//            throw new AppFeedbackNotExistedException(
//                    ResponseStatusEnum.FEEDBACKSNOTEXISTED.getErrorMessage(),
//                    ResponseStatusEnum.FEEDBACKSNOTEXISTED.getErrorMessage()
//            );
//        }
        // 检查反馈人信息
        UserInfoRpcDTO userInfo = userInfoRpc.getUserInfo(receiverId);
        // 用户不存在
        if (userInfo == null) {
            throw new UserNotExistedException(
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage(),
                    ResponseStatusEnum.USERNOTEXISTED.getErrorMessage()
            );
        }
        // 生成反馈记录id
        long recordId = snowFlakeService.snowflakeId();
        // 创建反馈对像
        Feedback feedback = new Feedback(recordId, feedbackId);
        // 创建接收人
        Receiver receiver = new Receiver(receiverId);
        // 填充 feedback
        feedback.setCreateTime(command.getCreateTime());
        feedback.setUpdateTime(command.getCreateTime());
        feedback.setProcessTime(null);
        feedback.setStatus(Feedback.STATUS_UNPROCESSED);
        // 添加feedback操作由 sweetcat-app-feedback 微服务，FeedbackSumbitedEvent 事件触发，故 informer 为空
        feedback.setInformer(null);
        feedback.setReceiver(receiver);
        feedback.setResponseContent("");
        // 加入db
        feedbackRepository.addOne(feedback);
    }

    public void processFeedback(ProcessFeedbackCommand command) {
        long processorId = command.getProcessorId();
        long recordId = command.getRecordId();
        // 处理时间
        long processTime = command.getProcessTime();
        // 客服回复的内容
        String responseContent = command.getResponseContent();
        // 检查id
        verifyIdFormatService.verifyIds(processorId, recordId);
        // 处理人（客服）是否存在
        CustomerServiceStaff staff = staffRepository.findByStaffId(processorId);
        // 不存在
        if (staff == null) {
            throw new CustomerServiceStaffNotExistedException(
                    ResponseStatusEnum.CustomerServiceStaffNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.CustomerServiceStaffNOTEXISTED.getErrorMessage()
            );
        }
        // 查询 feedback
        Feedback feedback = feedbackRepository.findOneByRecordId(recordId);
        // 反馈不存在
        if (feedback == null) {
            throw new AppFeedbackNotExistedException(
                    ResponseStatusEnum.FEEDBACKSNOTEXISTED.getErrorCode(),
                    ResponseStatusEnum.FEEDBACKSNOTEXISTED.getErrorMessage()
            );
        }
        Long staffId = staff.getStaffId();
        Informer informer = new Informer(staffId);
        feedback.setInformer(informer);
        feedback.setUpdateTime(processTime);
        feedback.setProcessTime(processTime);
        feedback.setResponseContent(responseContent);
        // 保存修改
        feedbackRepository.save(feedback);
        // 创建事件 FeedbackProcessedByCustomerServiceEvent
        FeedbackProcessedByCustomerServiceEvent feedbackProcessedByCustomerServiceEvent = new FeedbackProcessedByCustomerServiceEvent(feedback.getFeedbackId(), staffId);
        // 填充领域事件
        feedbackProcessedByCustomerServiceEvent.setProcessTime(processTime);
        feedbackProcessedByCustomerServiceEvent.setResponseContent(responseContent);
        // 发布领域时事件 FeedbackProcessedByCustomerServiceEvent
        eventPublisher.syncSend("customer_service_topic", feedbackProcessedByCustomerServiceEvent);
    }
}
