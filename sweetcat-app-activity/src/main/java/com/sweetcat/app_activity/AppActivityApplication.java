package com.sweetcat.app_activity;

import com.sweetcat.commons.global_exception_handler.GlobalExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/24-19:44
 * @Version: 1.0
 */
/**
 * GlobalExceptionHandler类位于包 com.sweetcat.common.global_exception_handler 包，
 * 默认 spring boot只扫描 com.sweetcat.app_activity，
 * 过不扫描 GlobalExceptionHandler，则全局异常处理将不好生效，
 * 故此处扩大了 spring boot 的扫包范围
 */
@SpringBootApplication(scanBasePackages = "com.sweetcat")
@EnableDiscoveryClient
public class AppActivityApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppActivityApplication.class, args);
    }
}
