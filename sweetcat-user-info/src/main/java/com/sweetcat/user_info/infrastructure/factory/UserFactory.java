package com.sweetcat.user_info.infrastructure.factory;

import com.sweetcat.user_info.domain.user.entity.User;
import com.sweetcat.user_info.infrastructure.po.UserPO;
import org.springframework.stereotype.Component;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/25-15:05
 * @Version: 1.0
 */
@Component
public class UserFactory {
    public User create(UserPO userPO) {
        return new User(
                userPO.getUserId(),
                userPO.getNickname(),
                userPO.getPassword(),
                userPO.getAvatarPath(),
                userPO.getGender(),
                userPO.getBirthday(),
                userPO.getVipLevel(),
                userPO.getReferrer(),
                userPO.getPersonalizedSignature(),
                userPO.getPhone(),
                userPO.getCreateTime(),
                userPO.getUpdateTime()
        );
    }

    /**
     * 创建一个 空的 User
     * @return
     */
    public User createEmpty() {
        User user = new User();
        user.setIsNew(true);
        user.setEctype(user);
        return user;
    }

}
