package com.czxy.changgou4.controller;

import com.czxy.changgou4.config.JwtProperties;
import com.czxy.changgou4.pojo.User;
import com.czxy.changgou4.service.OrderService;
import com.czxy.changgou4.utils.JwtUtils;
import com.czxy.changgou4.vo.BaseResult;
import com.czxy.changgou4.vo.OrderVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Resource
    private OrderService orderService;
    @Resource
    private JwtProperties jwtProperties;
    @Resource
    private HttpServletRequest request;

    @PostMapping
    public BaseResult createOrder(@RequestBody OrderVo orderVo) {
        //1 登录用户
        String token = request.getHeader("Authorization");
        User loginUser = null;
        try {
            loginUser = JwtUtils.getObjectFromToken(token, jwtProperties.getPublicKey(), User.class);
        } catch (Exception e) {
            return BaseResult.error("token失效");
        }

        try {
            //2 订单
            Long sn = orderService.createOrder(loginUser, orderVo);

            //3 返回
            return BaseResult.ok("订单创建成功").append("sn", sn + "");
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.error(e.getMessage());
        }
    }
}
