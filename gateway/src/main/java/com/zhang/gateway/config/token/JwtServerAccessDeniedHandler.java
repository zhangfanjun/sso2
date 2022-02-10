package com.zhang.gateway.config.token;

import com.alibaba.fastjson.JSON;
import com.zhang.gateway.filter.global.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;

/**
 * 无该权限，来自于JwtAccessManager处理的false结果
 * 这个跟security的设置相似，但是接口却不同
 * @Author: zhangfanjun
 * @Date 2021/11/17
 */
@Slf4j
@Component
public class JwtServerAccessDeniedHandler implements ServerAccessDeniedHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, AccessDeniedException e) {
        log.error("无访问权限");
        ServerHttpResponse response = serverWebExchange.getResponse();
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        String result = JSON.toJSONString(new CommonResponse("500", "无访问权限", null));
        DataBuffer buffer = response.bufferFactory().wrap(result.getBytes(Charset.forName("UTF-8")));
        return response.writeWith(Mono.just(buffer));
    }
}
