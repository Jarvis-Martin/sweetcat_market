package com.sweetcat.customerservice.domain.staff.repository;

import com.sweetcat.customerservice.domain.staff.entity.CustomerServiceStaff;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/13-11:20
 * @version: 1.0
 */
public interface CustomerServiceStaffRepository {
    /**
     * find by staff id
     * @param staffId
     * @return
     */
    CustomerServiceStaff findByStaffId(Long staffId);

    /**
     * 添加一个记录
     * @param staff
     */
    void addOne(CustomerServiceStaff staff);
}
