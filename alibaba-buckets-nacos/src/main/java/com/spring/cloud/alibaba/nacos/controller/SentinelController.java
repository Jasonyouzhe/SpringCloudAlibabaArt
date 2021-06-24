package com.spring.cloud.alibaba.nacos.controller;

//import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sentinel")
@RefreshScope
public class SentinelController {

    @RequestMapping("/hello")
//    @SentinelResource("hello")
    public String get() {
        return "sentinel";
    }
}