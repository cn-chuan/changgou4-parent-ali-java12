package com.czxy.changgou4;

import org.junit.Test;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
public class TestInt {

    @Test
    public void testInt() {
        Integer goodsCount = 1;
        Integer total = 3;

        System.out.println(goodsCount * 100d / total);
        System.out.println(String.format("%.2f", goodsCount * 100d / total));
    }
}
