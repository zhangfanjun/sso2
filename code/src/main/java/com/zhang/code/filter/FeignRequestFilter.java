package com.zhang.code.filter;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 请求中继器
 */
@Slf4j
@Component
public class FeignRequestFilter implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        log.info("封装请求");
        //获取请求
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //获取头部
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames!=null){
            while (headerNames.hasMoreElements()){
                String key = headerNames.nextElement();
                String value = request.getHeader(key);
                log.info("key:{},value:{}",key,value);
                //封装头部
                requestTemplate.header(key,value);
            }
        }
    }
}
