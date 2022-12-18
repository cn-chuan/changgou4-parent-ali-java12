package com.czxy.changgou4;

import com.czxy.changgou4.utils.PayState;
import com.czxy.changgou4.utils.PayStateClass;
import org.junit.Test;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
public class TestPayStateClass {

    @Test
    public void testPayClass() {
        System.out.println(PayStateClass.SUCCESS);
        System.out.println(PayStateClass.SUCCESS.getCode());
        System.out.println(PayStateClass.SUCCESS.getDesc());
    }

    @Test
    public void testPay() {
        System.out.println(PayState.SUCCESS);
        System.out.println(PayState.SUCCESS.getCode());
        System.out.println(PayState.SUCCESS.getDesc());
    }

}
