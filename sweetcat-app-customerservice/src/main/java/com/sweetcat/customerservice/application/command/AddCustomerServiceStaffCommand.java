package com.sweetcat.customerservice.application.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/13-11:51
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCustomerServiceStaffCommand {

    /**
     * 客服昵称
     */
    private String staffNickname;

    /**
     * 账号创建时间
     */
    private Long createTime;

}
