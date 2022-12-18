package com.czxy.changgou4.controller;

import com.czxy.changgou4.pojo.User;
import com.czxy.changgou4.service.UserService;
import com.czxy.changgou4.vo.BaseResult;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/checkusername")
    public BaseResult checkusername(@RequestBody User user) {
        //2 查询
        User findUser = userService.findByUsername(user.getUsername());
        //3 处理
        if(findUser == null) {
            return BaseResult.ok("用户名可用");
        }
        return BaseResult.error("用户名已占用");
    }

    @PostMapping("/checkmobile")
    public BaseResult checkmobile(@RequestBody User user) {
        //2 查询
        User findUser = userService.findByMobile(user.getMobile());
        //3 处理
        if(findUser == null) {
            return BaseResult.ok("手机号可用");
        }
        return BaseResult.error("手机号已占用");
    }

    @PostMapping("/register")
    public BaseResult register(@RequestBody User user) {
        // 1 数据有效性校验
        // 1.1 用户名
        User userByUsername = userService.findByUsername(user.getUsername());
        if(userByUsername != null) {
            return BaseResult.error("用户名已存在");
        }
        // 1.2 手机号
        User userByMobile = userService.findByMobile(user.getMobile());
        if(userByMobile != null) {
            return BaseResult.error("手机号已注册");
        }
        // 1.3 密码校验
        if(StringUtils.isBlank(user.getPassword())) {
            return BaseResult.error("密码不能为空");
        }
        if(! user.getPassword().equals(user.getPassword_confirm())) {
            return BaseResult.error("密码和确认密码不一致");
        }
        // 1.4 验证码（无效、错误）
        String key = "sms_register" + user.getMobile();
        String redisCode = stringRedisTemplate.opsForValue().get(key);
        stringRedisTemplate.delete(key);        //保证一次性，将redis中的数据删除掉
        if(redisCode == null) {
            return BaseResult.error("验证码无效");
        }
        if(!redisCode.equalsIgnoreCase(user.getCode())) {
            return BaseResult.error("验证码错误");
        }

        // 2 注册
        boolean result = userService.register(user);
        // 3 提示
        if(result) {
            return BaseResult.ok("注册成功");
        }
        return BaseResult.error("注册失败");
    }

    @PostMapping("/findByUsername")
    public BaseResult<User> findByUsername(@RequestBody User user) {
        // 查询
        User findUser = userService.findByUsername(user.getUsername());
        // 返回查询结果
        return BaseResult.ok("查询成功", findUser);
    }


}
