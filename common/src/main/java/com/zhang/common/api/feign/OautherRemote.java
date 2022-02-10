/*
 * Copyright © 2011-2015 Kinghood Group All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳金雅福控股集团有限公司 www.jinyafu.com.
 */
package com.zhang.common.api.feign;


import com.zhang.common.api.feign.fallback.OautherRemoteFallback;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @version 2019年11月4日下午1:57:24
 * @author Mike
 */
//@Headers("Content-Type:application/json")
@FeignClient(value = "oauther2",fallback = OautherRemoteFallback.class)
public interface OautherRemote {

    /**
     * 获取token
     * @author zfj
     */

    @PostMapping( value = "/oauther2/oauth/token")
    Object postAccessToken(@RequestBody MultiValueMap<String, String> parameters);
}
