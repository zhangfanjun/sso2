package com.zhang.gateway.config.token;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * 校验token，完成后交给鉴权管理
 */
@Slf4j
@Component
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {

    @Autowired
    private TokenStore tokenStore;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.justOrEmpty(authentication)
                .filter(x->x instanceof BearerTokenAuthenticationToken)
                .cast(BearerTokenAuthenticationToken.class)
                .map(BearerTokenAuthenticationToken::getToken)
                .flatMap(accessToken->{
                    //校验token是否有效和是否过期
                    OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(accessToken);
                    if (oAuth2AccessToken == null) {
                        //这里进行抛出了异常，所以在全局异常的捕捉中添加对异常的处理
                        return Mono.error(new InvalidTokenException("无效token"));
                    } else if (oAuth2AccessToken.isExpired()){
                        return Mono.error(new InvalidTokenException("token已过期"));
                    }
                    //读取权限
                    OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(accessToken);
                    if (oAuth2Authentication == null) {
                        return Mono.error(new InvalidTokenException("无效token"));
                    } else {
                        return Mono.just(oAuth2Authentication);
                    }
                }).cast(Authentication.class);
    }
}
