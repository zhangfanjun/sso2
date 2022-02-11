package com.zhang.common.model.constant;

import lombok.Data;

/**
 * @Copyright 深圳金雅福控股集团有限公司
 * @Author: zhangfanjun
 * @Date 2021/11/17
 * @Version: 1.0
 */

public enum HeaderEnum {
    /**
     * 用户名
     */
    HEADER_USER_NAME( "userName"),
    /**
     * 用户权限
     */
    HEADER_AUTHORITIES("authorities");

    private String name;

    /**
     * 构造方法
     */
    HeaderEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
