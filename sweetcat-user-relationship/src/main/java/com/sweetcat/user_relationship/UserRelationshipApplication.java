package com.sweetcat.user_relationship;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/2-22:24
 * @Version: 1.0
 */
@SpringBootApplication(
        exclude = {DruidDataSourceAutoConfigure.class},
        scanBasePackages = "com.sweetcat")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.sweetcat")
public class UserRelationshipApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserRelationshipApplication.class, args);
    }
}
