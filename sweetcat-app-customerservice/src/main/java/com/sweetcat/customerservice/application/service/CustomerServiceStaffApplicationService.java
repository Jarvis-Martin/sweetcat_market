package com.sweetcat.customerservice.application.service;

import com.sweetcat.customerservice.application.command.AddCustomerServiceStaffCommand;
import com.sweetcat.customerservice.domain.staff.entity.CustomerServiceStaff;
import com.sweetcat.customerservice.domain.staff.repository.CustomerServiceStaffRepository;
import com.sweetcat.customerservice.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.customerservice.infrastructure.service.snowflake_service.SnowFlakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/13-11:48
 * @version: 1.0
 */
@Service
public class CustomerServiceStaffApplicationService {
    private CustomerServiceStaffRepository staffRepository;
    private VerifyIdFormatService verifyIdFormatService;
    private SnowFlakeService snowFlakeService;

    @Autowired
    public void setSnowFlakeService(SnowFlakeService snowFlakeService) {
        this.snowFlakeService = snowFlakeService;
    }

    @Autowired
    public void setVerifyIdFormatService(VerifyIdFormatService verifyIdFormatService) {
        this.verifyIdFormatService = verifyIdFormatService;
    }

    @Autowired
    public void setStaffRepository(CustomerServiceStaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    /**
     * find by staff id
     * @param staffId
     * @return
     */
    @Transactional
    public CustomerServiceStaff findByStaffId(Long staffId) {
        // 检查id
        verifyIdFormatService.verifyIds(staffId);
        // 检查 staffId 是否存在
        return staffRepository.findByStaffId(staffId);
    }

    /**
     * 添加一个记录
     * @param command
     */
    @Transactional
    public void addOne(AddCustomerServiceStaffCommand command) {
        // 生成id
        long staffId = snowFlakeService.snowflakeId();
        // 创建 staff
        CustomerServiceStaff staff = new CustomerServiceStaff(staffId);
        staff.setStaffNickname(command.getStaffNickname());
        staff.setCreateTime(command.getCreateTime());
        staff.setUpdateTime(command.getCreateTime());
        // 添加
        staffRepository.addOne(staff);
    }
}
