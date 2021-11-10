package com.sweetcat.geography;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/10-13:49
 * @version: 1.0
 */
@SpringBootApplication()
@EnableDiscoveryClient
public class GeographyApplication {
    public static void main(String[] args) {
        SpringApplication.run(GeographyApplication.class, args);
    }
}
