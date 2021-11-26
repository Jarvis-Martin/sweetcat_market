package com.sweetcat.userinformation.application.rpc;

import com.sweetcat.api.rpcdto.customerservice.CustomerServiceStaffInfoRpcDTO;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/26-17:22
 * @version: 1.0
 */
public interface CustomerServiceInforRpc {
    /**
     * find by staff id
     * @param staffId
     * @return
     */
    CustomerServiceStaffInfoRpcDTO findByStaffId(Long staffId);
}
