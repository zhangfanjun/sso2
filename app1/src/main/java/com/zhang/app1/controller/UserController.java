package com.zhang.app1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = "get")
    public Object get(){
        log.info("请求访问了");

        return "";
    }
}
