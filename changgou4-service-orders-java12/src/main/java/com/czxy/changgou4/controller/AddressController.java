package com.czxy.changgou4.controller;

import com.czxy.changgou4.config.JwtProperties;
import com.czxy.changgou4.pojo.Address;
import com.czxy.changgou4.pojo.User;
import com.czxy.changgou4.service.AddressService;
import com.czxy.changgou4.utils.JwtUtils;
import com.czxy.changgou4.vo.BaseResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@RestController
@RequestMapping("/address")
public class AddressController {
    @Resource
    private AddressService addressService;

    @Resource
    private HttpServletRequest request;

    @Resource
    private JwtProperties jwtProperties;

    @GetMapping
    public BaseResult findAll() {
        //1 获得登录用户
        String token = request.getHeader("Authorization");
        User loginUser = null;
        try {
            loginUser = JwtUtils.getObjectFromToken(token, jwtProperties.getPublicKey(), User.class);
        } catch (Exception e) {
            return BaseResult.error("token失效");
        }
        //2 查询地址
        List<Address> addressList = addressService.findAllByUserId(loginUser.getId());
        //3 返回
        return BaseResult.ok("查询成功", addressList);
    }

    @PostMapping
    public BaseResult addAddress(@RequestBody Address address) {
        //1 获得登录用户
        String token = request.getHeader("Authorization");
        User loginUser = null;
        try {
            loginUser = JwtUtils.getObjectFromToken(token, jwtProperties.getPublicKey(), User.class);
        } catch (Exception e) {
            return BaseResult.error("token失效");
        }
        // 将用户信息存放地址中
        address.setUserId(loginUser.getId());
        //2 添加
        addressService.addAddress(address);
        //3 返回
        return BaseResult.ok("添加成功");
    }
}
