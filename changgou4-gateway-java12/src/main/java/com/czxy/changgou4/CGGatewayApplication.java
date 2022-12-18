package com.czxy.changgou4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CGGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(CGGatewayApplication.class, args);
    }
}
