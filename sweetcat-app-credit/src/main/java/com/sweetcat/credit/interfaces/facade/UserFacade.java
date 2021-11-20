package com.sweetcat.credit.interfaces.facade;

import com.sweetcat.credit.application.service.UserApplicationService;
import com.sweetcat.credit.domain.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/15-13:40
 * @version: 1.0
 */
@Component
public class UserFacade {
    private UserApplicationService userApplicationService;

    @Autowired
    public void setUserApplicationService(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    public User findOneByUserId(Long userId) {
        return userApplicationService.findOneByUserId(userId);
    }

    /**
     * 用户签到功能
     * @param userId
     */
    public void checkIn(Long userId) {
        userApplicationService.checkIn(userId);
    }

    /**
     * 用户兑换兑换商品
     *
     * @param userId
     * @param marketItemId
     */
    public void redeemCommodity(Long userId, Long marketItemId, Long createTime) {
        userApplicationService.redeemCommodity(userId, marketItemId, createTime);
    }
}
