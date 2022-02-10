package com.zhang.gateway.config.token;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * 从redis中获取到对应uri的权限，然后根据用户权限进行对比
 * 这里没有采用spring的鉴权方式，因为spring的鉴权方式需要对接口添加权限
 * @Author: zhangfanjun
 */
@Slf4j
@Component
public class JwtAccessManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        log.info("自定义权限校验");
        URI uri = authorizationContext.getExchange().getRequest().getURI();
        //TODO 从redis中获取uri的访问权限，这里采用allAuthority
        List<String> allAuthority= new ArrayList<>();
        allAuthority.add("admin");

        return //token验证通过(JwtAuthenticationManager)
                mono.filter(Authentication::isAuthenticated)
                //获取当前用户的权限
                .flatMapIterable(Authentication::getAuthorities).map(GrantedAuthority::getAuthority)
                //当用户的权限中存在uri允许访问的权限，则new一个AuthorizationDecision，全部不匹配，则返回false的对象
                .any(allAuthority::contains).map(AuthorizationDecision::new).defaultIfEmpty(new AuthorizationDecision(false));
    }
}
