package com.spring.cloud.alibaba.nacos;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArtFeignClientConfiguration {

    @Bean
    Logger.Level dispatchFeignLoggerLevel() {
        return Logger.Level.BASIC;
    }

    @Bean
    Logger dispatchFeignLogger() {
        return new ArtFeignLogger();
    }
}