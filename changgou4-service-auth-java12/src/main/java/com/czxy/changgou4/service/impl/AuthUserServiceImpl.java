package com.czxy.changgou4.service.impl;

import com.czxy.changgou4.domain.AuthUser;
import com.czxy.changgou4.feign.UserFeign;
import com.czxy.changgou4.service.AuthUserService;
import com.czxy.changgou4.utils.BCrypt;
import com.czxy.changgou4.vo.BaseResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Service
public class AuthUserServiceImpl implements AuthUserService {
    @Resource
    private UserFeign userFeign;

    @Override
    public AuthUser login(AuthUser authUser) {
        //1 查询用户
        BaseResult<AuthUser> userBaseResult = userFeign.findByUsername(authUser);
        AuthUser loginUser = userBaseResult.getData();
        //2 校验密码
        if(loginUser != null) {
            boolean checkpw = BCrypt.checkpw(authUser.getPassword(), loginUser.getPassword());
            if(checkpw) {
                return loginUser;
            }
        }
        //3 处理
        return null;
    }
}
