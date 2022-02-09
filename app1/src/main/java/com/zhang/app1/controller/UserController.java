package com.zhang.app1.controller;

import com.netflix.ribbon.proxy.annotation.Http;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @Copyright 深圳金雅福控股集团有限公司
 * @Author: zhangfanjun
 * @Date 2021/11/17
 * @Version: 1.0
 */
@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {

    @PostMapping("/login")
    public Object login(){
        return null;
    }

    @GetMapping(value = "/get")
    public Object get(@RequestHeader("username") String username,@RequestHeader("authorities") String authorities){
        log.info("请求访问了");
        HashMap<String, Object> re = new HashMap<>();
        re.put("username",username);
        re.put("authorities",authorities);
        return re;
    }
}
