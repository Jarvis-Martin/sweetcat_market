package com.sweetcat.user_info.interfaces.rpc;

import com.sweetcat.api.rpcdto.userinfo.UserInfoRpcDTO;
import com.sweetcat.user_info.domain.user.entity.User;
import com.sweetcat.user_info.interfaces.facade.UserInfoFacade;
import com.sweetcat.user_info.interfaces.facade.assembler.UserAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/4-20:53
 * @Version: 1.0
 */
@RestController
@RequestMapping("/rpc/user")
public class UserRpcController {
    private UserInfoFacade userInfoFacade;
    private UserAssembler userAssembler;

    @Autowired
    public void setFacade(UserInfoFacade userInfoFacade) {
        this.userInfoFacade = userInfoFacade;
    }

    @Autowired
    public void setUserAssembler(UserAssembler userAssembler) {
        this.userAssembler = userAssembler;
    }

    /**
     * 获得用户详情
     *
     * @param userId userId
     * @return 用户详情
     */
    @GetMapping("/{user_id}")
    public UserInfoRpcDTO getUserInfo(@PathVariable("user_id") Long userId) {
        User user = userInfoFacade.getUserInfo(userId);

        return new UserInfoRpcDTO(userId, user.getNickname(), user.getAvatarPath(), user.getPersonalizedSignature());
    }
}
