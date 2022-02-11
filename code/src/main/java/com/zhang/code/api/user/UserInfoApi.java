/*
 * Copyright © 2011-2015 Kinghood Group All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳金雅福控股集团有限公司 www.jinyafu.com.
 */
package com.zhang.code.api.user;


import com.zhang.code.api.user.fallback.UserInfoApiFallback;
import com.zhang.code.filter.FeignRequestFilter;
import com.zhang.common.model.module.base.ResoponseVO;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Mike
 * @version 2019年11月4日下午1:57:24
 */
@RequestMapping("/user/userInfo")
@Headers("Content-Type:application/json")
@FeignClient(value = "user", fallback = UserInfoApiFallback.class, configuration = FeignRequestFilter.class)
public interface UserInfoApi {

    @PostMapping("/returnUserName")
    ResoponseVO returnUserName();

}
