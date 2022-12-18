package com.czxy.changgou4.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.czxy.changgou4.pojo.User;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
public interface UserService extends IService<User> {
    /**
     * 通过用户名查询
     * @author 桐叔
     * @email liangtong@itcast.cn
     * @return
     */
    public User findByUsername(String username);

    /**
     * 通过手机号查询
     * @author 桐叔
     * @email liangtong@itcast.cn
     * @return
     */
    public User findByMobile(String mobile);

    /**
     * 注册
     * @author 桐叔
     * @email liangtong@itcast.cn
     * @return
     */
    boolean register(User user);
}
