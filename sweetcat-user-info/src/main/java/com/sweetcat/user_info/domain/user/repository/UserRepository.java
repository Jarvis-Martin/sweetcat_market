package com.sweetcat.user_info.domain.user.repository;

import com.sweetcat.user_info.domain.user.entity.User;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/25-15:00
 * @Version: 1.0
 */
public interface UserRepository {
    /**
     * find user by userId
     * @param userId userId
     * @return user info with userId
     */
    User find(Long userId);

    /**
     * 根据 phone 查找用户信息
     * @param phone phone
     * @return user Info with phone
     */
    User find(String phone);

    /**
     * 存储用户
     * @param user user
     */
    void save(User user);

    /**
     * 根据 参数更新 User
     * @param user user
     */
    void add(User user);
}
