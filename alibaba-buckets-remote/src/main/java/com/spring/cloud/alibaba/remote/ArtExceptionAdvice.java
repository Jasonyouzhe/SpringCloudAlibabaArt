package com.spring.cloud.alibaba.remote;

import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ArtExceptionAdvice {

    /**
     * 处理所有自定义宕机异常
     */
    @ExceptionHandler(FlowException.class)
    public void handleShotDownException(FlowException exception) throws FlowException {
        log.error(exception.getRule().getResource()+"被限流了,相信信息是: {}", JSON.toJSON(exception.getRule()));
        exception.setRuleLimitApp("***********************");
        throw new FlowException(exception.getRule().getResource()+"被限流了");
    }
}
