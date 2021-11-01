package com.sweetcat.app_carousel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/24-14:31
 * @Version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AppCarouselApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppCarouselApplication.class, args);
    }
}
