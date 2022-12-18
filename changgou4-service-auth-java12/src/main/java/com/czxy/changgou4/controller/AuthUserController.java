package com.czxy.changgou4.controller;

import com.czxy.changgou4.config.JwtProperties;
import com.czxy.changgou4.domain.AuthUser;
import com.czxy.changgou4.service.AuthUserService;
import com.czxy.changgou4.utils.JwtUtils;
import com.czxy.changgou4.vo.BaseResult;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@RestController
@RequestMapping("/auth")
public class AuthUserController {

    @Resource
    private AuthUserService authUserService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private JwtProperties jwtProperties;

    @PostMapping("/login")
    public BaseResult login(@RequestBody AuthUser authUser) {

        //1 校验：一次性验证码
        String key = "login" + authUser.getUsername();
        String redisVerifyCode = stringRedisTemplate.opsForValue().get(key);
        stringRedisTemplate.delete(key);
        if(redisVerifyCode == null) {
            return BaseResult.error("验证码无效");
        }
        if(! redisVerifyCode.equalsIgnoreCase(authUser.getCode())) {
            return BaseResult.error("验证码错误");
        }

        //2 登录
        AuthUser loginUser = authUserService.login(authUser);

        //3 提示
        if(loginUser != null) {
            String token = JwtUtils.generateToken(loginUser, jwtProperties.getExpire(), jwtProperties.getPrivateKey());
            // 成功
            return BaseResult.ok("登录成功").append("loginUser", loginUser).append("token", token);
        }
        // 失败
        return BaseResult.error("账号或密码不匹配");

    }
}
