package com.sweetcat.customerservice.interfaces.facade.assembler;

import com.sweetcat.api.rpcdto.customerservice.CustomerServiceStaffInfoRpcDTO;
import com.sweetcat.customerservice.domain.staff.entity.CustomerServiceStaff;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/26-17:13
 * @version: 1.0
 */
@Component
public class CustomerServiceStaffRpcAssembler {
    public CustomerServiceStaffInfoRpcDTO converterToCustomerServiceStaffInfoRpcDTO(CustomerServiceStaff staff) {
        CustomerServiceStaffInfoRpcDTO rpcDTO = new CustomerServiceStaffInfoRpcDTO();
        rpcDTO.setStaffId(staff.getStaffId());
        rpcDTO.setStaffNickname(staff.getStaffNickname());
        rpcDTO.setStaffAvatar(staff.getStaffAvatar());
        return rpcDTO;
    }
}
