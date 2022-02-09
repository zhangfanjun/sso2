package com.zhang.gateway.config.token;

import com.sun.deploy.util.ArrayUtil;
import com.zhang.gateway.properties.Oauther2Config;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.web.server.ServerBearerTokenAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.util.CollectionUtils;
import org.springframework.web.cors.reactive.CorsWebFilter;

/**
 * @Copyright 深圳金雅福控股集团有限公司
 * @Author: zhangfanjun
 * @Date 2021/11/17
 * @Version: 1.0
 */
@Configuration
@EnableWebFluxSecurity
public class GatewaySecurityConfig {
    @Autowired
    private JwtAccessManager jwtAccessManager;
    @Autowired
    private JwtServerAccessDeniedHandler jwtServerAccessDeniedHandler;
    @Autowired
    private JwtServerAuthenticationEntryPoint jwtServerAuthenticationEntryPoint;
    @Autowired
    private ReactiveAuthenticationManager authenticationManager;
    @Autowired
    private CorsWebFilter corsWebFilter;
    @Autowired
    private Oauther2Config oauther2Config;
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        //替换认证过滤器
        AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(authenticationManager);
        authenticationWebFilter.setServerAuthenticationConverter(new ServerBearerTokenAuthenticationConverter());
        //配置白名单和访问规则，CommonEnum枚举类
        http.httpBasic().disable()
                .csrf().disable()
                .authorizeExchange()
                //白名单放行
                .pathMatchers(oauther2Config.getIgnoreUri()).permitAll()
                //鉴权
                .anyExchange().access(jwtAccessManager)
                .and()
                //token异常处理
                .exceptionHandling()
                .authenticationEntryPoint(jwtServerAuthenticationEntryPoint)
                .accessDeniedHandler(jwtServerAccessDeniedHandler)
                .and()
                //处理跨域
                .addFilterAt(corsWebFilter, SecurityWebFiltersOrder.CORS)
                //替换token认证
                .addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION);
        return http.build();
    }
}
