package com.sweetcat.customerservice.interfaces.rpc;

import com.sweetcat.api.rpcdto.customerservice.CustomerServiceStaffInfoRpcDTO;
import com.sweetcat.customerservice.domain.staff.entity.CustomerServiceStaff;
import com.sweetcat.customerservice.interfaces.facade.CustomerServiceStaffFacade;
import com.sweetcat.customerservice.interfaces.facade.assembler.CustomerServiceStaffRpcAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/26-16:58
 * @version: 1.0
 */
@RestController
@RequestMapping("/rpc/customer_service")
public class CustomerServiceStaffRpcController {
    private CustomerServiceStaffFacade staffFacade;
    private CustomerServiceStaffRpcAssembler staffRpcAssembler;

    @Autowired
    public void setStaffRpcAssembler(CustomerServiceStaffRpcAssembler staffRpcAssembler) {
        this.staffRpcAssembler = staffRpcAssembler;
    }

    @Autowired
    public void setStaffFacade(CustomerServiceStaffFacade staffFacade) {
        this.staffFacade = staffFacade;
    }

    /**
     * find by staff id
     * @param staffId
     * @return
     */
    @GetMapping("/{staff_id}")
    public CustomerServiceStaffInfoRpcDTO findByStaffId(@PathVariable("staff_id") Long staffId) {
        CustomerServiceStaff staff = staffFacade.findByStaffId(staffId);
        return staffRpcAssembler.converterToCustomerServiceStaffInfoRpcDTO(staff);
    }


}
