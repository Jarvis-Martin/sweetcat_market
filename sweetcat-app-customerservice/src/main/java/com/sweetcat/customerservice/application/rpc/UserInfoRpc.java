package com.sweetcat.customerservice.application.rpc;

import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/9-19:45
 * @version: 1.0
 */
@Component
public interface UserInfoRpc {
    /**
     * 获得用户详情
     *
     * @param userId userId
     * @return 用户详情
     */
    UserInfoRpcDTO getUserInfo(Long userId);
}
