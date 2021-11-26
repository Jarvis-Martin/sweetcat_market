package com.sweetcat.userinformation.domain.information.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import com.sweetcat.userinformation.domain.information.vo.Publisher;
import com.sweetcat.userinformation.domain.information.vo.Receiver;
import lombok.Getter;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/25-16:28
 * @version: 1.0
 */
@Getter
public class Information {

    /**
     * 评论回复
     */
    public static final Integer TYPE_COMMENTREPLY = 0;
    /**
     * 系统通知
     */
    public static final Integer TYPE_SYSTEMINFORMATION = 1;
    /**
     * 未读状态
     */
    public static final Integer STATUS_UNREAD = 0;
    /**
     * 已读状态
     */
    public static final Integer STATUS_READ = 1;

    /**
     * 通知id
     */
    private Long informationId;
    /**
     * 发布人
     */
    private Publisher publisher;
    /**
     * 接收人
     */
    private Receiver receiver;
    /**
     * 内容
     */
    private String content;
    /**
     * 内容配图
     */
    private List<String> contentPics;
    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 通知状态
     */
    private Integer status;
    /**
     * 通知类型
     */
    private Integer type;

    /**
     * 标记通知为已读
     */
    public void markAsRead() {
        this.setStatus(Information.STATUS_READ);
    }

    /**
     * 标记通知为未读
     */
    public void markAsUnRead() {
        this.setStatus(Information.STATUS_UNREAD);
    }

    public Information(Long informationId) {
        this.informationId = informationId;
    }

    public void setInformationId(Long informationId) {
        if (informationId == null || informationId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.informationId = informationId;
    }

    public void setPublisher(Publisher publisher) {
        if (publisher == null) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.publisher = publisher;
    }


    public void setReceiver(Receiver receiver) {
        if (receiver == null) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.receiver = receiver;
    }

    public void setContent(String content) {
        if (content == null || content.length() <= 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.content = content;
    }

    public void setContentPics(List<String> contentPics) {
        if (contentPics == null || contentPics.size() <= 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.contentPics = contentPics;
    }

    public void setCreateTime(Long createTime) {
        if (createTime == null || createTime < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.createTime = createTime;
    }


    public void setStatus(Integer status) {
        if (status == null || status < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.status = status;
    }

    public void setType(Integer type) {
        if (type == null || type < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.type = type;
    }
}
