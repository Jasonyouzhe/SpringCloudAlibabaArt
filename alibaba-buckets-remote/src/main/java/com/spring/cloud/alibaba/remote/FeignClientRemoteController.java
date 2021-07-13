package com.spring.cloud.alibaba.remote;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.fastjson.JSON;
import com.spring.cloud.alibaba.remote.vo.UlityResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fr")
@Slf4j
public class FeignClientRemoteController {

    @GetMapping("/query")
    @SentinelResource(value = "fr/query",blockHandlerClass = SentinelException.class,
            blockHandler = "globalHandler")
//    @SentinelResource(value = "fr/query")
    public UlityResponse test(@RequestParam(value = "name") String name){
        return UlityResponse.ok(name);
    }
/*    public UlityResponse testError(String name, Throwable e) {
        if (e instanceof FlowException) {
            return UlityResponse.error(name,"被限流了");
        }
        return UlityResponse.error(name,"未知错误");
    }*/
}
