package com.zhang.gateway.filter.global;

import com.alibaba.fastjson.JSON;
import com.zhang.gateway.properties.Oauther2Config;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

/**
 * @Copyright 深圳金雅福控股集团有限公司
 * @Author: zhangfanjun
 * @Date 2021/11/17
 * @Version: 1.0
 */
@Slf4j
@Component
@Order(value = 1)
public class RequestHeaderSetFilter implements GlobalFilter {
    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private Oauther2Config oauther2Config;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("封装请求头");
        String requestUrl = exchange.getRequest().getPath().value();
        //判断是否在白名单的范围
        List<String> ignoreUriList = oauther2Config.getIgnoreUriList();
        if (!CollectionUtils.isEmpty(ignoreUriList) && ignoreUriList.contains(requestUrl)) {
            log.info("白名单放行");
            return chain.filter(exchange);
        }
        String token = getToken(exchange);
        if (StringUtils.isBlank(token)) {
            log.error("无效token");
            return Mono.error(new InvalidTokenException("无效token"));
        }
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        Map<String, Object> additionalInformation = oAuth2AccessToken.getAdditionalInformation();
        String username = additionalInformation.get("user_name").toString();
        List<String> authories = (List<String>) additionalInformation.get("authorities");
        //将用户信息封装到请求头，也可以采用base64进行加密放一个请求头中，微服务采用过滤器进行解密
        ServerHttpRequest tokenRequest = exchange.getRequest().mutate()
                .header("username", username)
                .header("authorities", JSON.toJSONString(authories)).build();
        ServerWebExchange exchangeNew = exchange.mutate().request(tokenRequest).build();

        return chain.filter(exchangeNew);
    }

    private String getToken(ServerWebExchange exchange) {
        List<String> authorization = exchange.getRequest().getHeaders().get("Authorization");
        if (CollectionUtils.isEmpty(authorization)) {
            return null;
        }
        String bear = authorization.get(0);
        if (bear.contains("Bearer ")) {
            String token = bear.substring("Bearer ".length());
            log.info("token:{}",token);
            return token;
        }
        return null;
    }
}
