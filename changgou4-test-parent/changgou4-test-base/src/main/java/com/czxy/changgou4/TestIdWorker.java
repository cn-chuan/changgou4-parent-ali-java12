package com.czxy.changgou4;

import com.czxy.changgou4.utils.IdWorker;
import org.junit.Test;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
public class TestIdWorker {

    @Test
    public void testCreateId() {
        // 创建核心类
        IdWorker idWorker = new IdWorker(1, 1);
        // 生成唯一的id
        long l = idWorker.nextId();
        System.out.println(l);
    }
}
