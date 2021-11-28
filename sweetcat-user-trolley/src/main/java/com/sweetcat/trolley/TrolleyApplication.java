package com.sweetcat.trolley;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/28-14:33
 * @version: 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.sweetcat"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.sweetcat.api"})
public class TrolleyApplication {
    public static void main(String[] args) {
        SpringApplication.run(TrolleyApplication.class, args);
    }
}
