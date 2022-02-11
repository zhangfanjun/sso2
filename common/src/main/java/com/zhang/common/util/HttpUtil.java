package com.zhang.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @Copyright 深圳金雅福控股集团有限公司
 * @Author: zhangfanjun
 * @Date 2021/11/17
 * @Version: 1.0
 */
@Slf4j
public class HttpUtil {
    private static RestTemplate restTemplate = new RestTemplate();

    /**
     * 远程http请求
     */
    public static String remoteHttp(String url, HttpMethod httpMethod, Map<String, String> inHeader, String inBody) {
        HttpHeaders httpHeaders = new HttpHeaders();
        //json请求格式
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        //封装头部信息
        if (!CollectionUtils.isEmpty(inHeader)) {
            inHeader.forEach((x, y) -> {
                httpHeaders.add(x, y);
            });
        }
        //将头部信息和body进行封装
        HttpEntity<String> entity = new HttpEntity<>(inBody, httpHeaders);
        ResponseEntity<String> exchange = restTemplate.exchange(url, httpMethod, entity, String.class);
        if (exchange.getStatusCode() != HttpStatus.OK) {
            log.error("请求响应异常");
            return null;
        }
        return exchange.getBody();
    }

    /**
     * post请求
     */
    public static String remotePost(String url, Map<String, String> inHeader, String inBody) {
        return remoteHttp(url,HttpMethod.POST,inHeader,inBody);
    }
}
