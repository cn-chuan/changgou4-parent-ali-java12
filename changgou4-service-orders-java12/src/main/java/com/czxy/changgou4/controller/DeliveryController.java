package com.czxy.changgou4.controller;

import com.czxy.changgou4.config.JwtProperties;
import com.czxy.changgou4.pojo.Delivery;
import com.czxy.changgou4.pojo.User;
import com.czxy.changgou4.service.DeliveryService;
import com.czxy.changgou4.utils.JwtUtils;
import com.czxy.changgou4.vo.BaseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
    @Resource
    private HttpServletRequest request;

    @Resource
    private JwtProperties jwtProperties;

    @Resource
    private DeliveryService deliveryService;

    @GetMapping
    public BaseResult<ArrayList<Delivery>> getAll(){
        //1 获得登录用户
        String token = request.getHeader("Authorization");
        User loginUser = null;
        try {
            loginUser = JwtUtils.getObjectFromToken(token, jwtProperties.getPublicKey(), User.class);
        } catch (Exception e) {
            return BaseResult.error("token失效");
        }
        ArrayList<Delivery> list= deliveryService.findAll(loginUser.getId());
        return BaseResult.ok("查询成功",list);
    }
}
