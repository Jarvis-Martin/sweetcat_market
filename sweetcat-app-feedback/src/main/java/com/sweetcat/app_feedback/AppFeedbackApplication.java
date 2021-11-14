package com.sweetcat.app_feedback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/6-17:57
 * @Version: 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.sweetcat"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.sweetcat.api")
public class AppFeedbackApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppFeedbackApplication.class, args);
    }
}
