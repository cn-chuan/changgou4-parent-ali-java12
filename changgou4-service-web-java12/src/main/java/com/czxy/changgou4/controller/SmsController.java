package com.czxy.changgou4.controller;

import com.aliyuncs.exceptions.ClientException;
import com.czxy.changgou4.pojo.User;
import com.czxy.changgou4.utils.SmsUtil;
import com.czxy.changgou4.vo.BaseResult;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@RestController
@RequestMapping("/sms")
public class SmsController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping
    public BaseResult sendSms(@RequestBody User user) {
        try {
            //1 获得随机验证码(4位数字)  ---> [1000, 10000)
            int code = RandomUtils.nextInt(1000, 10000);
            //RandomStringUtils.randomNumeric(4);       //随机4为数字的字符串
            //2 将随机验证码存放到redis中 -- 时效5分钟
            String key = "sms_register" + user.getMobile();
            stringRedisTemplate.opsForValue().set(key, code + "", 5 , TimeUnit.MINUTES);
            System.out.println("验证码：" + code);
            //3 以短信的方式，将验证码发送给用户
            SmsUtil.sendSms(user.getMobile(), user.getUsername(), code + "", "","");
            //4 提示
            return BaseResult.ok("短信发送成功，请查收");
        } catch (ClientException e) {
            e.printStackTrace();        //打印异常，使用日志替换
            return BaseResult.error("短信发送失败");
        }
    }
}
