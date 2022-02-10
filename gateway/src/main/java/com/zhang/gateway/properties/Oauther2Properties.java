package com.zhang.gateway.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @Copyright 深圳金雅福控股集团有限公司
 * @Author: zhangfanjun
 * @Date 2021/11/17
 * @Version: 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "oauther2")
public class Oauther2Properties {
    String[] ignoreUri;

    public List<String> getIgnoreUriList() {
        if (ignoreUri != null && ignoreUri.length > 0) {
            List<String> ignoreList = Arrays.asList(ignoreUri);
            return ignoreList;
        }
        return null;
    }
}
