package com.sweetcat.customerservice.infrastructure.factory;

import com.sweetcat.customerservice.domain.staff.entity.CustomerServiceStaff;
import com.sweetcat.customerservice.infrastructure.po.CustomerserviceStaffInfoPO;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/13-11:29
 * @version: 1.0
 */
@Component
public class CustomerServiceStaffFactory {
    public CustomerServiceStaff create(CustomerserviceStaffInfoPO staffInfoPO) {
        CustomerServiceStaff customerServiceStaff = new CustomerServiceStaff(staffInfoPO.getStaffId());
        customerServiceStaff.setStaffNickname(staffInfoPO.getStaffNickname());
        customerServiceStaff.setCreateTime(staffInfoPO.getCreateTime());
        customerServiceStaff.setUpdateTime(staffInfoPO.getUpdateTime());
        return customerServiceStaff;
    }
}
