package com.sweetcat.customerservice;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/12-22:41
 * @version: 1.0
 */
@SpringBootApplication(
        exclude = {DruidDataSourceAutoConfigure.class},
        scanBasePackages = {"com.sweetcat"})
@EnableDiscoveryClient
@EnableFeignClients("com.sweetcat.api")
public class AppCustomerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppCustomerServiceApplication.class, args);
    }
}
