package com.czxy.changgou4.controller;

import com.czxy.changgou4.cart.Cart;
import com.czxy.changgou4.config.JwtProperties;
import com.czxy.changgou4.pojo.User;
import com.czxy.changgou4.service.CartService;
import com.czxy.changgou4.utils.JwtUtils;
import com.czxy.changgou4.vo.BaseResult;
import com.czxy.changgou4.vo.CartVo;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@RestController
@RequestMapping("/carts")
public class CartController {

    @Resource
    private CartService cartService;

    @Resource
    private JwtProperties jwtProperties;

    @Resource
    private HttpServletRequest request;

    @PostMapping
    public BaseResult addCart(@RequestBody CartVo cartVo) {
        //1 获得登录用户
        // 1.1 获得token
        String token = request.getHeader("Authorization");
        // 1.2 解析
        User loginUser = null;
        try {
            loginUser = JwtUtils.getObjectFromToken(token, jwtProperties.getPublicKey(), User.class);
        } catch (Exception e) {
            return BaseResult.error("token失效或未登录");
        }

        //2 添加操作
        cartService.addCart(loginUser, cartVo);

        //3 提示
        return BaseResult.ok("添加成功");
    }

    @GetMapping
    public BaseResult queryCartList() {
        //1 登录用户
        String token = request.getHeader("Authorization");
        User loginUser = null;
        try {
            loginUser = JwtUtils.getObjectFromToken(token, jwtProperties.getPublicKey(), User.class);
        } catch (Exception e) {
            return BaseResult.error("token失效");
        }
        //2 查询
        Cart cart = cartService.queryCartList(loginUser);
        //3 返回
        return BaseResult.ok("查询成功", cart.getData().values());
    }

    @PutMapping
    public BaseResult updateCart(@RequestBody List<CartVo> cartVoList) {
        //1 登录用户
        String token = request.getHeader("Authorization");
        User loginUser = null;
        try {
            loginUser = JwtUtils.getObjectFromToken(token, jwtProperties.getPublicKey(), User.class);
        } catch (Exception e) {
            return BaseResult.error("token失效");
        }
        //2 更新
        cartService.updateCart(loginUser, cartVoList);

        //3 提示
        return BaseResult.ok("购物车更新成功");
    }
}
