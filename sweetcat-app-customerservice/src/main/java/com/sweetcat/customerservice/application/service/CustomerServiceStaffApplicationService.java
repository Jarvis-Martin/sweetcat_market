package com.sweetcat.customerservice.application.service;

import com.sweetcat.customerservice.application.command.AddCustomerServiceStaffCommand;
import com.sweetcat.customerservice.domain.staff.entity.CustomerServiceStaff;
import com.sweetcat.customerservice.domain.staff.repository.CustomerServiceStaffRepository;
import com.sweetcat.customerservice.infrastructure.service.id_format_verfiy_service.VerifyIdFormatService;
import com.sweetcat.customerservice.infrastructure.service.snowflake_service.SnowFlakeService;
import com.sweetcat.customerservice.infrastructure.service.timestamp_format_verfiy_service.VerifyTimeStampFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private VerifyTimeStampFormatService verifyTimeStampFormatService;
    private SnowFlakeService snowFlakeService;

    @Autowired
    public void setVerifyTimeStampFormatService(VerifyTimeStampFormatService verifyTimeStampFormatService) {
        this.verifyTimeStampFormatService = verifyTimeStampFormatService;
    }

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
    public void addOne(AddCustomerServiceStaffCommand command) {
        // 检查 创建时间
        verifyTimeStampFormatService.verifyTimeStamps(command.getCreateTime());
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
