package com.czxy.changgou4.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 */
@Data
//@Configuration
@ConfigurationProperties(prefix = "sc.filter")
public class FilterProperties {

    private List<String> allowPaths;            //允许访问的路径（白名单）
}
