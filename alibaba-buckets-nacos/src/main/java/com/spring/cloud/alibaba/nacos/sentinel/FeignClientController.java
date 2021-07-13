package com.spring.cloud.alibaba.nacos.sentinel;

import com.alibaba.nacos.common.utils.ExceptionUtil;
import com.spring.cloud.alibaba.nacos.feign.FeignClientTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang.exception.ExceptionUtils;
@RestController
@RequestMapping("/fc")
public class FeignClientController {

    @Autowired
    FeignClientTest feignClientTest;

    @GetMapping("/query")
    public String fc(){
        String str = null;
        try {
            str =  feignClientTest.test("zhangsan");
        }catch (Exception e){
            System.out.println("异常"+ ExceptionUtils.getFullStackTrace(e));
        }
        return str;
    }
}
