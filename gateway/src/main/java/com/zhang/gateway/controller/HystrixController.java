package com.zhang.gateway.controller;

import com.alibaba.fastjson.JSON;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Copyright 深圳金雅福控股集团有限公司
 * @Author: zhangfanjun
 * @Date 2021/11/17
 * @Version: 1.0
 */
@RequestMapping("/hystrix")
@RestController
public class HystrixController {

    /**
     * 熔断是一个重定向，但是会造成异常信息的隐藏
     * */
    @RequestMapping("/defaultError")
    public Object defaultHystrix(){
        return "请求熔断";
    }
}
