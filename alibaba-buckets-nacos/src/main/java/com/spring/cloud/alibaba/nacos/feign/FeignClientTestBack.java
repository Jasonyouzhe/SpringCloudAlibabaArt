package com.spring.cloud.alibaba.nacos.feign;

import org.springframework.stereotype.Component;

@Component
public class FeignClientTestBack implements FeignClientTest{
    @Override
    public String test(String name){
        return "调用失败:/fr/query: "+ name;
    }
}
