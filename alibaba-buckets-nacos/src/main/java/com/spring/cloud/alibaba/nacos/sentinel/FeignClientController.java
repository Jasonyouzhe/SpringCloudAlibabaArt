package com.spring.cloud.alibaba.nacos.sentinel;

import com.spring.cloud.alibaba.nacos.feign.FeignClientTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fc")
public class FeignClientController {

    @Autowired
    FeignClientTest feignClientTest;

    @GetMapping("/query")
    public String fc(){
        String str =  feignClientTest.test("zhangsan");
        return str;
    }
}
