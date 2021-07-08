package com.spring.cloud.alibaba.buckets;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Application {
    public static void main(String[] args) {
//        ConfigurableApplicationContext contextApplition;
        SpringApplication.run(Application.class, args);
//        for (String beanDefinitionName : contextApplition.getBeanDefinitionNames()) {
//            System.out.println("beanDefinitionName: "+beanDefinitionName);
//        }
    }
}
