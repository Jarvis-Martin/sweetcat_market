package com.sweetcat.userinformation.infrastructure.po;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * t_user_information
 * @author 
 */
@Data
public class InformationPO implements Serializable {
    /**
     * 通知id
     */
    private Long informationId;

    /**
     * 发布人id
     */
    private Long publisherId;

    /**
     * 发布人昵称
     */
    private String publisherName;

    /**
     * 发布人头像
     */
    private String publisherAvatar;

    /**
     * 接收人id
     */
    private Long receiverId;

    /**
     * 通知内容
     */
    private String content;

    /**
     * 通知配图
     */
    private List<String> contentPics;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 状态 0未读 1已读
     */
    private Integer status;

    /**
     * 通知类型
     */
    private Integer type;

    private static final long serialVersionUID = 1L;
}