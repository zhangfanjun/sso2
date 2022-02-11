package com.zhang.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//用于feign调用，因为这里路径与本项目的路径不一致，所以需要添加bean的路径
@EnableFeignClients(basePackages = "com.zhang.common.api.oauther2")
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
