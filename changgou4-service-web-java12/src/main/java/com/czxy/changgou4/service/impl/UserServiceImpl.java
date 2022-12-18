package com.czxy.changgou4.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czxy.changgou4.mapper.UserMapper;
import com.czxy.changgou4.pojo.User;
import com.czxy.changgou4.service.UserService;
import com.czxy.changgou4.utils.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public User findByUsername(String username) {
        return baseMapper.findByUsername(username);
    }

    @Override
    public User findByMobile(String mobile) {
        //1 查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        //2 查询
        List<User> list = baseMapper.selectList(queryWrapper);
        if(list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public boolean register(User user) {
        // 对密码进行加密
        String newPassword = BCrypt.hashpw(user.getPassword());
        user.setPassword(newPassword);

        // 再保存用户信息
        int insert = baseMapper.insert(user);

        return insert == 1;
    }
}
