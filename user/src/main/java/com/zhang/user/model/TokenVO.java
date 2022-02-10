package com.zhang.user.model;

import lombok.Data;

import java.io.Serializable;


@Data
public class TokenVO implements Serializable {
    /**
     * 临时令牌
     */
    private String access_token;
    /**
     * token类型
     */
    private String token_type;
    /**
     * 刷新令牌
     */
    private String refresh_token;
    /**
     * 失效时间
     */
    private Long expires_in;
    /**
     * 访问范围
     */
    private String scope;
    /**
     * uuid
     */
    private String jti;
}
