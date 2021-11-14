package com.sweetcat.customerservice.infrastructure.repository;

import com.sweetcat.customerservice.domain.staff.entity.CustomerServiceStaff;
import com.sweetcat.customerservice.domain.staff.repository.CustomerServiceStaffRepository;
import com.sweetcat.customerservice.infrastructure.dao.CustomerserviceStaffInfoMapper;
import com.sweetcat.customerservice.infrastructure.factory.CustomerServiceStaffFactory;
import com.sweetcat.customerservice.infrastructure.po.CustomerserviceStaffInfoPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/13-11:24
 * @version: 1.0
 */
@Repository
public class CustomerServiceStaffRepositoryImpl implements CustomerServiceStaffRepository {
    private CustomerserviceStaffInfoMapper staffInfoMapper;
    private CustomerServiceStaffFactory staffFactory;

    @Autowired
    public void setStaffInfoMapper(CustomerserviceStaffInfoMapper staffInfoMapper) {
        this.staffInfoMapper = staffInfoMapper;
    }

    @Autowired
    public void setStaffFactory(CustomerServiceStaffFactory staffFactory) {
        this.staffFactory = staffFactory;
    }

    /**
     * find by staff id
     * @param staffId
     * @return
     */
    @Override
    public CustomerServiceStaff findByStaffId(Long staffId) {
        CustomerserviceStaffInfoPO staffInfoPO = staffInfoMapper.findByStaffId(staffId);
        if (staffInfoPO == null) {
            return null;
        }
        return staffFactory.create(staffInfoPO);
    }

    /**
     * 添加一个记录
     * @param staff
     */
    @Override
    public void addOne(CustomerServiceStaff staff) {
        staffInfoMapper.addOne(staff);
    }
}
