package com.sweetcat.storeorder;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/4-21:20
 * @version: 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.sweetcat"}, exclude = {DruidDataSourceAutoConfigure.class})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.sweetcat.api"})
public class StoreOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(StoreOrderApplication.class, args);
    }
}
