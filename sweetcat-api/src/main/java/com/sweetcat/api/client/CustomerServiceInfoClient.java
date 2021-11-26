package com.sweetcat.api.client;

import com.sweetcat.api.rpcdto.customerservice.CustomerServiceStaffInfoRpcDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/26-17:19
 * @version: 1.0
 */
@Component
@FeignClient("sweetcat-app-customerservice")
public interface CustomerServiceInfoClient {
    /**
     * find by staff id
     * @param staffId
     * @return
     */
    @GetMapping("/rpc/customer_service/{staff_id}")
    CustomerServiceStaffInfoRpcDTO findByStaffId(@PathVariable("staff_id") Long staffId);
}
