package com.sweetcat.customerservice.infrastructure.dao;

import com.sweetcat.customerservice.domain.staff.entity.CustomerServiceStaff;
import com.sweetcat.customerservice.infrastructure.po.CustomerserviceStaffInfoPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerserviceStaffInfoMapper {

    /**
     * find by staff id
     * @param staffId
     * @return
     */
    CustomerserviceStaffInfoPO findByStaffId(Long staffId);

    /**
     * 添加一个记录
     * @param staff
     */
    void addOne(CustomerServiceStaff staff);
}