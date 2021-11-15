package com.sweetcat.credit.infrastructure.po;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * t_app_user_coin
 * @author 
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPO implements Serializable {
    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 用户积分总数
     */
    private Long totalCredit;

    /**
     * 创建时间
     */
    private Long createTime;

    private static final long serialVersionUID = 1L;
}