package com.sweetcat.customerservice.interfaces.facade;

import com.sweetcat.customerservice.application.command.AddCustomerServiceStaffCommand;
import com.sweetcat.customerservice.application.service.CustomerServiceStaffApplicationService;
import com.sweetcat.customerservice.domain.staff.entity.CustomerServiceStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/13-14:48
 * @version: 1.0
 */
@Component
public class CustomerServiceStaffFacade {
    private CustomerServiceStaffApplicationService staffApplicationService;

    @Autowired
    public void setStaffApplicationService(CustomerServiceStaffApplicationService staffApplicationService) {
        this.staffApplicationService = staffApplicationService;
    }

    /**
     * find by staff id
     * @param staffId
     * @return
     */
    public CustomerServiceStaff findByStaffId(Long staffId) {
        return staffApplicationService.findByStaffId(staffId);
    }

    /**
     * 添加一个记录
     * @param command
     */
    public void addOne(AddCustomerServiceStaffCommand command) {
        staffApplicationService.addOne(command);
    }
}
