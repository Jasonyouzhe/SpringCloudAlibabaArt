package com.spring.cloud.alibaba.nacos.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Component
//@FeignClient(name = "fr", url = "${com.remote.test.uri:http://192.168.9.130:8762}",fallback = FeignClientTestBack.class)
@FeignClient(name = "fr", url = "${com.remote.test.uri:http://192.168.9.130:8762}")
public interface FeignClientTest {

    @GetMapping("/fr/query")
    String test(@RequestParam(value = "name") String name);
}
