package com.spring.cloud.alibaba.remote;


import com.spring.cloud.alibaba.remote.vo.UlityResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;

public class SentinelException{

    public static UlityResponse globalHandler(String name,BlockException e){
        UlityResponse response = null;
        if (e instanceof FlowException) {
            response = UlityResponse.error(name,"被限流了");
        } else if (e instanceof DegradeException) {
            response = UlityResponse.error(name,"服务降级了");
        } else if (e instanceof ParamFlowException) {
            response = UlityResponse.error(name,"被限流了");
        } else if (e instanceof SystemBlockException) {
            response = UlityResponse.error(name,"被系统保护了");
        } else if (e instanceof AuthorityException) {
            response = UlityResponse.error(name,"被授权了");
        }
        return response;
    }
}

