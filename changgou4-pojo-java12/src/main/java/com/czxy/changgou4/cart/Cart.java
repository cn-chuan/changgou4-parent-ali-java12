package com.czxy.changgou4.cart;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Data
public class Cart {
    // 购物项列表（为了快速获得指定的购物项，采用Map<skuId, 购物项>）
    private Map<Integer, CartItem> data = new HashMap<>();
    // 总计（总价）
    private Double total;

    /**
     * 获得总价
     * 总价：所有商品小计的和
     * 小计：商品的单价 * 商品的数量
     * @author 桐叔
     * @email liangtong@itcast.cn
     * @return
     */
    public Double getTotal() {
        double sum = 0;
        for(Map.Entry<Integer,CartItem> entry : data.entrySet()) {
            CartItem cartItem = entry.getValue();
            if(cartItem.getChecked()) {
                sum += (cartItem.getPrice() * cartItem.getCount());
            }
        }
        return sum;
    }

    /**
     * 将指定商品添加到购物车
     * 1. 如果没有，直接添加
     * 2. 如果有，更新数量
     * @author 桐叔
     * @email liangtong@itcast.cn
     * @return
     */
    public void addCart(CartItem cartItem){
        //1 从map获得购物项
        CartItem cache = data.get(cartItem.getSkuid());
        if(cache == null) {
            //2.1 如果没有，添加
            data.put(cartItem.getSkuid(), cartItem);
        } else {
            //2.2 如果有，更新数量
            cache.setCount(  cache.getCount() + cartItem.getCount() );
        }
    }

    /**
     * 更新购买的数量和勾选状态
     * @author 桐叔
     * @email liangtong@itcast.cn
     * @return
     */
    public void updateCart(Integer skuId, Integer count, Boolean checked) {
        //1 获得项
        CartItem cartItem = data.get(skuId);
        //2 如果有，更改数据
        if(cartItem != null) {
            cartItem.setCount(count);
            cartItem.setChecked(checked);
        }
    }

    /**
     * 删除指定商品
     * @author 桐叔
     * @email liangtong@itcast.cn
     * @return
     */
    public void deleteCart(Integer skuId) {
        data.remove(skuId);
    }

    /**
     * 清空购物车
     * @author 桐叔
     * @email liangtong@itcast.cn
     * @return
     */
    public void clearCart() {
        data.clear();
    }
}
