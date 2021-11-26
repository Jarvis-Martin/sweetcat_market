package com.sweetcat.userinformation.interfaces.facade.restdto;

import lombok.Data;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/25-22:14
 * @version: 1.0
 */
@Data
public class InformationRestDTO {

    /**
     * 通知id
     */
    private Long informationId;
    /**
     * 发布人
     */
    private PublisherRestDTO publisher;
    /**
     * 接收人
     */
    private ReceiverRestDTO receiver;
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

}
