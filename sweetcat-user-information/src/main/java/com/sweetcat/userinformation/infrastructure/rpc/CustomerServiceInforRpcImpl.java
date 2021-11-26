package com.sweetcat.userinformation.infrastructure.rpc;

import com.sweetcat.api.client.CustomerServiceInfoClient;
import com.sweetcat.api.rpcdto.customerservice.CustomerServiceStaffInfoRpcDTO;
import com.sweetcat.userinformation.application.rpc.CustomerServiceInforRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/26-17:24
 * @version: 1.0
 */
@Component
public class CustomerServiceInforRpcImpl implements CustomerServiceInforRpc {
    private CustomerServiceInfoClient customerServiceInfoClient;

    @Autowired
    public void setCustomerServiceInfoClient(CustomerServiceInfoClient customerServiceInfoClient) {
        this.customerServiceInfoClient = customerServiceInfoClient;
    }

    /**
     * find by staff id
     * @param staffId
     * @return
     */
    @Override
    public CustomerServiceStaffInfoRpcDTO findByStaffId(Long staffId) {
        return customerServiceInfoClient.findByStaffId(staffId);
    }
}
