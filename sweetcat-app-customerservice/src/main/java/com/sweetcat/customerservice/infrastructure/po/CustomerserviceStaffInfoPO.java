package com.sweetcat.customerservice.infrastructure.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * t_app_customerservice_staff_info
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerserviceStaffInfoPO implements Serializable {
    /**
     * 客服id
     */
    private Long staffId;

    /**
     * 客服昵称
     */
    private String staffNickname;

    /**
     * 账号创建时间
     */
    private Long createTime;

    /**
     * 账号更新时间
     */
    private Long updateTime;

    private static final long serialVersionUID = 1L;
}