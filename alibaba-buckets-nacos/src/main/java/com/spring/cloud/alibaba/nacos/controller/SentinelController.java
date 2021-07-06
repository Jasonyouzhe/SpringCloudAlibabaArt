package com.spring.cloud.alibaba.nacos.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sentinel")
@RefreshScope
public class SentinelController {

    @RequestMapping("/h")
    @SentinelResource("h")
    public String get() {
        try {
            //睡4s，准备降级
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "sentinel";
    }
}