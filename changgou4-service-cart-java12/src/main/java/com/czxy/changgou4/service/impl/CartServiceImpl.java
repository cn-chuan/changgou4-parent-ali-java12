package com.czxy.changgou4.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.czxy.changgou4.cart.Cart;
import com.czxy.changgou4.cart.CartItem;
import com.czxy.changgou4.feign.SkuFeign;
import com.czxy.changgou4.pojo.User;
import com.czxy.changgou4.service.CartService;
import com.czxy.changgou4.vo.BaseResult;
import com.czxy.changgou4.vo.CartOneSkuResult;
import com.czxy.changgou4.vo.CartVo;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Service
public class CartServiceImpl implements CartService {
    @Resource
    private SkuFeign skuFeign;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public void addCart(User loginUser, CartVo cartVo) {
        //1 获得商品详情
        BaseResult<CartOneSkuResult> baseResult = skuFeign.findSkuById(cartVo.getSkuid());
        CartOneSkuResult cartOneSkuResult = baseResult.getData();

        //2 从redis中获得购物车
        String cartKey = "cart" + loginUser.getId();
        String cartStr = stringRedisTemplate.opsForValue().get(cartKey);
        Cart cart = null;
        if(cartStr != null) {
            cart = JSONObject.parseObject(cartStr, Cart.class);
        } else {
            cart = new Cart();
        }

        //3 将详情转换CartItem，并添加到购物车
        CartItem cartItem = new CartItem();
        cartItem.setSkuid(cartOneSkuResult.getSkuid());
        cartItem.setSpuid(cartOneSkuResult.getSpuid());
        cartItem.setGoodsName(cartOneSkuResult.getGoodsName());
        cartItem.setPrice(cartOneSkuResult.getPrice());
        cartItem.setCount(cartVo.getCount());
        cartItem.setChecked(cartVo.getChecked());
        cartItem.setMidlogo(cartOneSkuResult.getLogo().get("biglogo"));
        cartItem.setSpecInfo(cartOneSkuResult.getSpecInfo());
        cart.addCart(cartItem);

        //4 将购物车保存redis
        cartStr = JSONObject.toJSONString(cart);
        stringRedisTemplate.opsForValue().set(cartKey, cartStr);

    }

    @Override
    public Cart queryCartList(User loginUser) {
        //1 获得购物车
        String key = "cart" + loginUser.getId();
        String cartStr = stringRedisTemplate.opsForValue().get(key);
        //2 返回
        Cart cart = JSONObject.parseObject(cartStr, Cart.class);
        return cart;
    }

    @Override
    public void updateCart(User loginUser, List<CartVo> cartVoList) {
        //1 获得购物车
        String key = "cart" + loginUser.getId();
        String cartStr = stringRedisTemplate.opsForValue().get(key);
        if(cartStr == null) {
            throw new RuntimeException("购物车不存在");
        }
        Cart cart = JSONObject.parseObject(cartStr, Cart.class);

        //2 处理前端购物车中的数据  List --> Map<skuId, CartVo>
        Map<Integer, CartVo> cartVoMap = new HashMap<>();
        cartVoList.forEach(cartVo -> {
            cartVoMap.put(cartVo.getSkuid(), cartVo);
        });

        //3 遍历购物车中的数据（Cart购物车生成新的Set）
        // 3.1 创建新的set集合，存放购物车的所有商品id
        Set<Integer> idSet = new HashSet<>(cart.getData().keySet());
        // 3.2 遍历新集合
        idSet.forEach(skuId -> {
            CartVo cartVo = cartVoMap.get(skuId);
            if(cartVo != null) {
                // 3.2.1 在cartVoMap存在，将更新数据（数量、状态）
                cart.updateCart(cartVo.getSkuid(), cartVo.getCount(), cartVo.getChecked());
            } else {
                // 3.2.2 才cartVoMap不存在，将删除数据
                cart.deleteCart(skuId);
            }
        });

        //4 保存购物车
        stringRedisTemplate.opsForValue().set(key, JSONObject.toJSONString(cart));
    }
}
