package com.zhang.app1.controller;

import com.zhang.code.api.user.UserInfoApi;
import com.zhang.common.model.module.base.ResoponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class App1Controller {

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

    @Autowired
    private UserInfoApi userInfoApi;

    @PostMapping("/toUser")
    public ResoponseVO toUser(){
        ResoponseVO resoponseVO = userInfoApi.returnUserName();
        return resoponseVO;
    }

}
