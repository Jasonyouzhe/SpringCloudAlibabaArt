package com.spring.cloud.alibaba.nacos;

import feign.Request;
import feign.Response;
import feign.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static feign.Util.*;

public class ArtFeignLogger extends feign.Logger {

    private final Logger logger;

    public ArtFeignLogger() {
        this(feign.Logger.class);
    }

    public ArtFeignLogger(Class<?> clazz) {
        this(LoggerFactory.getLogger(clazz));
    }

    public ArtFeignLogger(String name) {
        this(LoggerFactory.getLogger(name));
    }

    ArtFeignLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    protected void logRequest(String configKey, Level logLevel, Request request) {
        if (logger.isInfoEnabled()) {
            log(configKey, "FeignClient Request ---> %s %s HTTP/1.1", request.method(), request.url());

            if (logger.isDebugEnabled()) {
                for (String field : request.headers().keySet()) {
                    for (String value : valuesOrEmpty(request.headers(), field)) {
                        log(configKey, "%s: %s", field, value);
                    }
                }
            }

            int bodyLength = 0;
            if (request.body() != null) {
                bodyLength = request.body().length;
                String bodyText = request.charset() != null ? new String(request.body(), request.charset()) : null;
                log(configKey, "%s", bodyText != null ? bodyText : "Binary data");
            }
            log(configKey, "FeignClient Request ---> END HTTP (%s-byte body)", bodyLength);
        }
    }

    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime) throws IOException {
        if (logger.isInfoEnabled()) {
            String reason = response.reason() != null && logLevel.compareTo(Level.NONE) > 0 ? " " + response.reason() : "";
            int status = response.status();
            log(configKey, "FeignClient Response <--- HTTP/1.1 %s%s (%sms)", status, reason, elapsedTime);

            if (logger.isDebugEnabled()) {
                for (String field : response.headers().keySet()) {
                    for (String value : valuesOrEmpty(response.headers(), field)) {
                        log(configKey, "%s: %s", field, value);
                    }
                }
            }

            int bodyLength = 0;
            if (response.body() != null && !(status == 204 || status == 205)) {
                // HTTP 204 No Content "...response MUST NOT include a message-body"
                // HTTP 205 Reset Content "...response MUST NOT include an entity"
                byte[] bodyData = Util.toByteArray(response.body().asInputStream());
                bodyLength = bodyData.length;
                if (bodyLength > 0) {
                    log(configKey, "%s", decodeOrDefault(bodyData, UTF_8, "Binary data"));
                }
                log(configKey, "FeignClient Response <--- END HTTP (%s-byte body)", bodyLength);
                return response.toBuilder().body(bodyData).build();
            } else {
                log(configKey, "FeignClient Response <--- END HTTP (%s-byte body)", bodyLength);
            }
            return response;
        }
        return response;
    }

    @Override
    protected void log(String configKey, String format, Object... args) {
        if (logger.isInfoEnabled()) {
            logger.info(String.format(methodTag(configKey) + format, args));
        }
    }
}
