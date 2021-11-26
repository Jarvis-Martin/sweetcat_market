package com.sweetcat.userinformation.infrastructure.po;

import java.io.Serializable;
import lombok.Data;

/**
 * t_user_system_information
 * @author 
 */
@Data
public class SystemInformationPO implements Serializable {
    /**
     * 通知id
     */
    private Long informationId;

    /**
     * 反馈处理时间
     */
    private Long processTime;

    /**
     * 响应给用户的标题
     */
    private String responseTitle;

    private static final long serialVersionUID = 1L;
}