package com.sweetcat.user_info.infrastructure.repository;

import com.alibaba.druid.filter.AutoLoad;
import com.sweetcat.user_info.domain.user.entity.User;
import com.sweetcat.user_info.domain.user.repository.UserRepository;
import com.sweetcat.user_info.infrastructure.dao.UserMapper;
import com.sweetcat.user_info.infrastructure.factory.UserFactory;
import com.sweetcat.user_info.infrastructure.po.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/25-15:01
 * @Version: 1.0
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    private UserMapper userMapper;
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    @Autowired
    public void setUserFactory(UserFactory userFactory) {
        this.userFactory = userFactory;
    }

    private UserFactory userFactory;

    @Override
    public User find(Long userId) {
        UserPO userPO = userMapper.getOne(userId);
        return userFactory.create(userPO);
    }

    @Override
    public User find(String phone) {
        UserPO userPO = userMapper.getOneByPhone(phone);
        if (userPO == null) {
            return null;
        }
        return userFactory.create(userPO);
    }

    @Override
    public void save(User user) {
//        if (user.getIsNew()) {
//            System.out.println("some thind error in Repository save method");
//            userMapper.insertOne(user);
//        }
//
        userMapper.update(user);
    }
}

