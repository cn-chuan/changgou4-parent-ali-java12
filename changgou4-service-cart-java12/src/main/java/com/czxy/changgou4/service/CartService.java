package com.czxy.changgou4.service;

import com.czxy.changgou4.cart.Cart;
import com.czxy.changgou4.cart.CartItem;
import com.czxy.changgou4.pojo.User;
import com.czxy.changgou4.vo.CartVo;

import java.util.List;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
public interface CartService {
    /**
     * 添加到购物车(skuid,count,checked)
     * @author 桐叔
     * @email liangtong@itcast.cn
     * @return
     */
    public void addCart(User loginUser, CartVo cartVo);

    /**
     * 查询指定用户的购物车信息
     * @author 桐叔
     * @email liangtong@itcast.cn
     * @return
     */
    public Cart queryCartList(User loginUser);

    /**
     * 更新购物车
     * @param loginUser 登录用户
     * @param cartVoList 购物车更新的列表项
     * @author 桐叔
     * @email liangtong@itcast.cn
     * @return
     */
    public void updateCart(User loginUser, List<CartVo> cartVoList);
}
