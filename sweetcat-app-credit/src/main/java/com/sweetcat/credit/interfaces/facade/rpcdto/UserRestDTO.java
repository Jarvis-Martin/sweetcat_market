package com.sweetcat.credit.interfaces.facade.rpcdto;

import lombok.Data;

import java.io.Serializable;

/**
 * t_app_user_coin
 * @author 
 */
@Data
public class UserRestDTO implements Serializable {
    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 用户积分总数
     */
    private Long credit;

    private static final long serialVersionUID = 1L;
}