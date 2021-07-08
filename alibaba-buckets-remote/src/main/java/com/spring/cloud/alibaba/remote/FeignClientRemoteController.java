package com.spring.cloud.alibaba.remote;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fr")
public class FeignClientRemoteController {

    @GetMapping("/query")
    @SentinelResource(value = "fr/query")
    public String test(@RequestParam(value = "name") String name){
        return "fr/query,"+name;
    }
//
//    public String testError(String name, Throwable e){
//        return "query error,"+name;
//    }
}
