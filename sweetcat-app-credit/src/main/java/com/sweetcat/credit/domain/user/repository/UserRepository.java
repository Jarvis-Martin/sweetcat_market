package com.sweetcat.credit.domain.user.repository;

import com.sweetcat.credit.domain.user.entity.User;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/14-19:17
 * @version: 1.0
 */
public interface UserRepository {
    /**
     * 添加一个
     * @param user
     */
    void addOne(User user);
    /**
     * find user-credit by userIf
     *
     * @param userId
     * @return
     */
    User findOneByUserId(Long userId);

    /**
     * 保存user的更新
     * @param user
     */
    void save(User user);
}
