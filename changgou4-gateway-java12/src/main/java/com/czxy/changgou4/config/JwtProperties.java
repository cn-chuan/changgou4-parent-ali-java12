package com.czxy.changgou4.config;

import com.czxy.changgou4.utils.RsaUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 */
@Configuration
@ConfigurationProperties(prefix = "sc.jwt")
@Data
public class JwtProperties {
    private String secret;              //登录校验的密钥
    private String pubKeyPath;          //公钥地址
    private String priKeyPath;          //私钥地址
    private Integer expire;             //过期时间,单位分钟
    private PublicKey publicKey;
    private PrivateKey privateKey;

    @PostConstruct      //初始化方法注解
    public void init() {
        try {
            File pubFile = new File(pubKeyPath);
            File priFile = new File(priKeyPath);
            if(!pubFile.exists() || ! priFile.exists()) {
                RsaUtils.generateKey(pubKeyPath,priKeyPath,secret);
            }
            // 获得公钥和私钥对象
            this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
            this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
