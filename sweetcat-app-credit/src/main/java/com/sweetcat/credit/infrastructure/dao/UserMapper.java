package com.sweetcat.credit.infrastructure.dao;

import com.sweetcat.credit.domain.user.entity.User;
import com.sweetcat.credit.infrastructure.po.UserPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    /**
     * find user-credit by userIf
     *
     * @param userId
     * @return
     */
    UserPO findOneByUserId(Long userId);

    /**
     * 保存user的更新
     *
     * @param user
     */
    void update(User user);

    void addOne(User user);
}