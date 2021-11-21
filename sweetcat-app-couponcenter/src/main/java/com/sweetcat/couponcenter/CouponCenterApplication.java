package com.sweetcat.couponcenter;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/20-20:05
 * @version: 1.0
 */
@SpringBootApplication(scanBasePackages = "com.sweetcat", exclude = {DruidDataSourceAutoConfigure.class})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.sweetcat.api"})
public class CouponCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(CouponCenterApplication.class, args);
    }
}
