package com.sweetcat.credit.infrastructure.factory;

import com.sweetcat.credit.domain.user.entity.User;
import com.sweetcat.credit.infrastructure.po.UserPO;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/14-19:20
 * @version: 1.0
 */
@Component
public class UserFactory {
    public User create(UserPO creditPO) {
        User user = new User(creditPO.getUserId());
        user.setCredit(creditPO.getTotalCredit());
        user.setCreateTime(creditPO.getCreateTime());
        return user;
    }
}
