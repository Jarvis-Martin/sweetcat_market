package com.sweetcat.user_info;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/25-14:32
 * @Version: 1.0
 */
@SpringBootApplication(
        exclude = {DruidDataSourceAutoConfigure.class},
        scanBasePackages = {"com.sweetcat"})
@EnableDiscoveryClient
public class UserInfoApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserInfoApplication.class, args);
    }
}
