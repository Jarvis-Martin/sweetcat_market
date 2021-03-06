package com.sweetcat.storeinfo;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-15:24
 * @Version: 1.0
 */
@SpringBootApplication(
        exclude = {DruidDataSourceAutoConfigure.class},
        scanBasePackages = {"com.sweetcat"})
@EnableDiscoveryClient
public class StoreInfoApplication {
    public static void main(String[] args) {
        SpringApplication.run(StoreInfoApplication.class, args);
    }
}
