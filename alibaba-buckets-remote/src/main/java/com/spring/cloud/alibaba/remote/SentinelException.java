package com.spring.cloud.alibaba.remote;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.spring.cloud.alibaba.remote.vo.MyResponse;
import org.springframework.context.annotation.Configuration;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class SentinelException implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
        MyResponse errorResponse = new MyResponse();
        // 不同的异常返回不同的提示语
        if (e instanceof FlowException) {
            errorResponse.setMsg("被限流了");
            errorResponse.setStatus(1);
        } else if (e instanceof DegradeException) {
            errorResponse.setMsg("服务降级了");
            errorResponse.setStatus(2);
        } else if (e instanceof ParamFlowException) {
            errorResponse.setMsg("被限流了");
            errorResponse.setStatus(3);
        } else if (e instanceof SystemBlockException) {
            errorResponse.setMsg("被系统保护了");
            errorResponse.setStatus(4);
        } else if (e instanceof AuthorityException) {
            errorResponse.setMsg("被授权了");
            errorResponse.setStatus(5);
        }
        httpServletResponse.setStatus(500);
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.getWriter().print(new Gson().toJson(errorResponse));
    }

    /*@Override
    public void blocked(HttpServletRequest request, HttpServletResponse response, BlockException e) throws IOException {
        MyResponse errorResponse = new MyResponse();
        // 不同的异常返回不同的提示语
        if (e instanceof FlowException) {
            errorResponse.setMsg("被限流了");
            errorResponse.setStatus(1);
        } else if (e instanceof DegradeException) {
            errorResponse.setMsg("服务降级了");
            errorResponse.setStatus(2);
        } else if (e instanceof ParamFlowException) {
            errorResponse.setMsg("被限流了");
            errorResponse.setStatus(3);
        } else if (e instanceof SystemBlockException) {
            errorResponse.setMsg("被系统保护了");
            errorResponse.setStatus(4);
        } else if (e instanceof AuthorityException) {
            errorResponse.setMsg("被授权了");
            errorResponse.setStatus(5);
        }

        response.setStatus(500);
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(new Gson().toJson(errorResponse));
    }*/
}

