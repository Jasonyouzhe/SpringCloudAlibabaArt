//package com.spring.cloud.alibaba.nacos.feign;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class FeignConfig {
//    /**
//     * 打印请求日志
//     * <p>
//     * NONE: 不记录任何信息
//     * BASIE:仅记录请求方法，URL以及响应状态码和执行时间
//     * HEADERS:除了记录BASIE级别得信息之外，还会记录请求和响应得头信息
//     * FULL：记录所有请求与响应得明细，包括头信息，请求体，元数据等。
//     *
//     * @return
//     */
//    @Bean
//    public feign.Logger.Level multipartLoggerLevel() {
//        return feign.Logger.Level.FULL;
//    }
//}
