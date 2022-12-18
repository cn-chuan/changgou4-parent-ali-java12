package com.czxy.changgou4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient      //服务发现
@EnableFeignClients         //远程调用
public class CGSearchServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CGSearchServiceApplication.class, args);
    }
}
