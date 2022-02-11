package com.zhang.oauther2.mysql.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Copyright 深圳金雅福控股集团有限公司
 * @Author: zhangfanjun
 * @Date 2021/11/17
 * @Version: 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "datasource")
public class BaseDataSourceProperties {
    protected  String username;
    protected  String password;
    protected  String url;
    protected  String driverClassName;
    protected  String mapperLoacations;
}
