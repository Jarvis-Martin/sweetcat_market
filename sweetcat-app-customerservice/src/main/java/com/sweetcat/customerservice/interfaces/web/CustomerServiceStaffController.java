package com.sweetcat.customerservice.interfaces.web;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.responsedto.ResponseDTO;
import com.sweetcat.customerservice.application.command.AddCustomerServiceStaffCommand;
import com.sweetcat.customerservice.interfaces.facade.CustomerServiceStaffFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/13-14:53
 * @version: 1.0
 */
@RestController
@RequestMapping("/customer_service")
public class CustomerServiceStaffController {
    private CustomerServiceStaffFacade staffFacade;

    @Autowired
    public void setStaffFacade(CustomerServiceStaffFacade staffFacade) {
        this.staffFacade = staffFacade;
    }

    /**
     * 添加一个记录
     *
     * @param command
     */
    @PostMapping("/add")
    public ResponseDTO addOne(AddCustomerServiceStaffCommand command) {
        staffFacade.addOne(command);
        return response("插入成功", "{}");
    }

    /**
     * 通用的放回 ResponseDTO
     *
     * @param tip  用户提示信息
     * @param data 数据部分
     * @return ResponseDTO
     */
    private ResponseDTO response(String tip, Object data) {
        return new ResponseDTO(
                ResponseStatusEnum.SUCCESS.getErrorCode(),
                ResponseStatusEnum.SUCCESS.getErrorMessage(),
                tip,
                data
        );
    }
}
