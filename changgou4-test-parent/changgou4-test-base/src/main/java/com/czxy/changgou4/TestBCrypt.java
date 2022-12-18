package com.czxy.changgou4;

import com.czxy.changgou4.utils.BCrypt;
import org.junit.Test;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
public class TestBCrypt {
    @Test
    public void testBCrypt() {
        // 加密
        String hashpw = BCrypt.hashpw("1234");
        System.out.println(hashpw);

        // 校验
        boolean checkpw = BCrypt.checkpw("12345", hashpw);
        System.out.println(checkpw);

    }
}
