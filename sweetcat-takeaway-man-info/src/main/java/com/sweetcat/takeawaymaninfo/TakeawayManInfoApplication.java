package com.sweetcat.takeawaymaninfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-13:37
 * @Version: 1.0
 */
@SpringBootApplication(scanBasePackages = "com.sweetcat")
@EnableDiscoveryClient
public class TakeawayManInfoApplication {
    public static void main(String[] args) {
        SpringApplication.run(TakeawayManInfoApplication.class,args);
    }
}
