package com.sweetcat.user_info.infrastructure.dao;

import com.sweetcat.user_info.domain.user.entity.User;
import com.sweetcat.user_info.infrastructure.po.UserPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    /**
     * 根据 userId 获得用户信息
     * @param userId userId
     * @return 用户信息 with userId
     */
    UserPO getOne(Long userId);

    /**
     * 根据 phone 查找用户信息
     * @param phone phone
     * @return user info with phone
     */
    UserPO getOneByPhone(String phone);

    void insertOne(User user);

    /**
     * 更新用户信息
     * @param user user
     */
    void update(User user);
}