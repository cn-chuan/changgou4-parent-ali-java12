package com.czxy.changgou4;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.czxy.changgou4.utils.SmsUtil;
import org.junit.Test;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
public class TestSms {
    @Test
    public void testSms() throws ClientException {
        int code = 9527;
        System.out.println("验证码；"  + code);
        SendSmsResponse response = SmsUtil.sendSms("13699282444", "张三", code + "", "", "");
        System.out.println(response.getCode());
        System.out.println(response.getMessage());
    }
}
