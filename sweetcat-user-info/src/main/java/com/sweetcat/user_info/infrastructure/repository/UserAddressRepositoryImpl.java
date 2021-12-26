package com.sweetcat.user_info.infrastructure.repository;

import com.sweetcat.user_info.domain.address.entity.UserAddress;
import com.sweetcat.user_info.domain.address.repository.UserAddressRepository;
import com.sweetcat.user_info.infrastructure.dao.UserAddressMapper;
import com.sweetcat.user_info.infrastructure.factory.UserAddressFactory;
import com.sweetcat.user_info.infrastructure.po.UserAddressPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/25-15:03
 * @Version: 1.0
 */
@Repository
public class UserAddressRepositoryImpl implements UserAddressRepository {


    @Autowired
    public void setUserAddressMapper(UserAddressMapper userAddressMapper) {
        this.userAddressMapper = userAddressMapper;
    }

    @Autowired
    public void setUserAddressFactory(UserAddressFactory userAddressFactory) {
        this.userAddressFactory = userAddressFactory;
    }

    private UserAddressMapper userAddressMapper;
    private UserAddressFactory userAddressFactory;

    @Override
    public UserAddress find(Long addressId) {
        UserAddressPO userAddressPO = this.userAddressMapper.findOne(addressId);
        if (userAddressPO == null) {
            return null;
        }
        return this.userAddressFactory.create(userAddressPO);
    }

    @Override
    public UserAddress findDefaultAddress(Long userId) {
        UserAddressPO defaultAddressPO = userAddressMapper.findDefaultAddress(userId);
        if (defaultAddressPO == null) {
            return null;
        }
        return userAddressFactory.create(defaultAddressPO);
    }

    @Override
    public List<UserAddress> find(Long userId, Integer page, Integer limit) {
        List<UserAddressPO> addressPOPage = this.userAddressMapper.getPage(userId, page, limit);
        if (addressPOPage == null || addressPOPage.isEmpty()) {
            return Collections.emptyList();
        }
        return addressPOPage.stream().collect(
                ArrayList::new,
                (con, addressPO) -> con.add(this.userAddressFactory.create(addressPO)),
                ArrayList::addAll
        );
    }

    @Override
    public void add(UserAddress address) {
        this.userAddressMapper.addOne(address);
    }

    @Override
    public void remove(UserAddress userAddress) {
        this.userAddressMapper.remove(userAddress);
    }

    @Override
    public UserAddress save(UserAddress userAddress) {
        this.userAddressMapper.update(userAddress);
        UserAddressPO userAddressPO = userAddressMapper.findOne(userAddress.getAddressId());
        if (userAddressPO == null) {
            return null;
        }
        return userAddressFactory.create(userAddressPO);
    }
}
