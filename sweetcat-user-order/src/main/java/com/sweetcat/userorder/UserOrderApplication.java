package com.sweetcat.userorder;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/29-13:44
 * @version: 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.sweetcat"}, exclude = {DruidDataSourceAutoConfigure.class})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.sweetcat.api"})
public class UserOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserOrderApplication.class, args);
    }
}
