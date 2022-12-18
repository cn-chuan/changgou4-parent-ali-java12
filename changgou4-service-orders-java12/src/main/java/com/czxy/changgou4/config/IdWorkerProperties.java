package com.czxy.changgou4.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Data
@ConfigurationProperties(prefix = "sc.worker")
public class IdWorkerProperties {

    private long workerId;          // 当前机器id

    private long datacenterId;      // 序列号

}
