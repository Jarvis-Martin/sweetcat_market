package com.sweetcat.credit.infrastructure.repository;

import com.sweetcat.credit.domain.user.entity.User;
import com.sweetcat.credit.domain.user.repository.UserRepository;
import com.sweetcat.credit.infrastructure.dao.UserMapper;
import com.sweetcat.credit.infrastructure.factory.UserFactory;
import com.sweetcat.credit.infrastructure.po.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/14-19:19
 * @version: 1.0
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
    private UserFactory userFactory;
    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setUserFactory(UserFactory userFactory) {
        this.userFactory = userFactory;
    }

    @Override
    public void addOne(User user) {
        userMapper.addOne(user);
    }

    /**
     * find user-credit by userIf
     *
     * @param userId
     * @return
     */
    @Override
    public User findOneByUserId(Long userId) {
        UserPO userPO = userMapper.findOneByUserId(userId);
        if (userPO == null) {
            return null;
        }
        return userFactory.create(userPO);
    }

    /**
     * 保存user的更新
     * @param user
     */
    @Override
    public void save(User user) {
        userMapper.update(user);
    }
}
