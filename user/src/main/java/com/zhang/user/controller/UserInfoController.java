package com.zhang.user.controller;

import com.zhang.common.model.constant.HeaderConstant;
import com.zhang.common.model.module.base.ResoponseVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Copyright 深圳金雅福控股集团有限公司
 * @Author: zhangfanjun
 * @Date 2021/11/17
 * @Version: 1.0
 */
@RequestMapping("/userInfo")
@RestController
public class UserInfoController {


    @PostMapping("/returnUserName")
    public ResoponseVO returnUserName(@RequestHeader(HeaderConstant.HEADER_USER_NAME) String userName){
        //根据账号，将用户进行退出登录处理
        return ResoponseVO.success(userName);
    }
}
