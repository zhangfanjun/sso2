package com.zhang.user.controller;

import com.alibaba.fastjson.JSON;
import com.zhang.common.api.feign.OautherRemote;
import com.zhang.common.model.base.ResoponseVO;
import com.zhang.user.model.LoginDTO;
import com.zhang.user.model.RefreshTokenDTO;
import com.zhang.user.model.TokenVO;
import com.zhang.user.properties.UserBaseProperties;
import com.zhang.user.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Objects;

@Slf4j
@RequestMapping("/oauther")
@RestController
public class OautherController {

    @Autowired
    private UserBaseProperties userBaseProperties;
    @Resource
    private OautherRemote oautherRemote;

    @PostMapping("/login")
    public ResoponseVO login(@RequestBody LoginDTO in){
        log.info("登录");
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("username", in.getUserName());
        multiValueMap.add("password", in.getPassword());
        multiValueMap.add("grant_type", "password");
        multiValueMap.add("scope", "all");
        multiValueMap.add("client_id", "user");
        multiValueMap.add("client_secret", "123");
        Object result = oautherRemote.postAccessToken(multiValueMap);
        TokenVO tokenVO = null;
        if(Objects.nonNull(result)) {
            String s = JSON.toJSONString(result);
            log.info("response:{}",s);
            tokenVO = JSON.parseObject(JSON.toJSONString(result), TokenVO.class);
        }
        if (Objects.isNull(tokenVO)) {
            return ResoponseVO.fault("异常",result);
        }
        return ResoponseVO.success(tokenVO);
    }

    @PostMapping("/refreshToken")
    public ResoponseVO refreshToken(@RequestBody RefreshTokenDTO in){
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("refresh_token", in.getRefreshToken());
        multiValueMap.add("grant_type", "refresh_token");
        multiValueMap.add("scope", "all");
        multiValueMap.add("client_id", "user");
        multiValueMap.add("client_secret", "123");
        Object result = oautherRemote.postAccessToken(multiValueMap);
        TokenVO tokenVO = null;
        if(Objects.nonNull(result)) {
            tokenVO = JSON.parseObject(JSON.toJSONString(result), TokenVO.class);
        }
        if (Objects.isNull(tokenVO)) {
            return ResoponseVO.fault("异常",result);
        }
        return ResoponseVO.success(tokenVO);
    }

    @PostMapping("/httpLogin")
    public String httpLogin(){
        String url = "http://127.0.0.1:8800/oauther2/oauth/token?grant_type=password&username=aa&password=123456&scope=ROLE_ADMIN&client_id=app-one&client_secret=123";
        String re = HttpUtil.remotePost(url, null, null);
        return re;
    }



}
