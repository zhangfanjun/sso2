package com.zhang.gateway.config.token;

import com.alibaba.fastjson.JSON;
import com.zhang.gateway.filter.global.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;

/**
 * 无效和过期令牌的处理，处理来自JwtAuthenticationManager的error结果
 * 这个跟security的设置相似，但是接口却不同
 * @Author: zhangfanjun
 */
@Slf4j
@Component
public class JwtServerAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {
    @Override
    public Mono<Void> commence(ServerWebExchange serverWebExchange, AuthenticationException e) {
        log.error("无效和过期令牌的处理");
        ServerHttpResponse response = serverWebExchange.getResponse();
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        String result = JSON.toJSONString(new CommonResponse("500", "token无效", null));
        DataBuffer buffer = response.bufferFactory().wrap(result.getBytes(Charset.forName("UTF-8")));
        return response.writeWith(Mono.just(buffer));
    }
}
