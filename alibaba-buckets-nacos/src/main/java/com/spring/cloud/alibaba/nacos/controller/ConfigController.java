package com.spring.cloud.alibaba.nacos.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    @Value("${nacos.app.config.user.name}")
    private String userName;

    @RequestMapping("/get")
    public String get() {
        System.out.println("值是："+userName);
        return userName;
    }
}