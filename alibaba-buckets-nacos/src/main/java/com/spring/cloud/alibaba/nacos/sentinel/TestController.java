package com.spring.cloud.alibaba.nacos.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sentinel")
public class TestController {

    @GetMapping("/test")
    @SentinelResource(value = "test", fallback = "testError")
    public String test(@RequestParam(value = "name") String name){
        return "hello,"+name;
    }

    public String testError(String name, Throwable e){
        return "error,"+name;
    }

    @RequestMapping("/hello")
    @SentinelResource(value = "hello", fallback = "testError1")
    public String get() {
        try {
            //睡4s，准备降级
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "sentinel";
    }
    public String testError1(Throwable e){
        return "error,hello";
    }
}
