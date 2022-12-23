package com.czxy.changgou4.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czxy.changgou4.cart.Cart;
import com.czxy.changgou4.cart.CartItem;
import com.czxy.changgou4.constant.CGConstant;
import com.czxy.changgou4.feign.SkuFeign;
import com.czxy.changgou4.mapper.AddressMapper;
import com.czxy.changgou4.mapper.OrderGoodsMapper;
import com.czxy.changgou4.mapper.OrderMapper;
import com.czxy.changgou4.pojo.Address;
import com.czxy.changgou4.pojo.Order;
import com.czxy.changgou4.pojo.OrderGoods;
import com.czxy.changgou4.pojo.User;
import com.czxy.changgou4.service.OrderService;
import com.czxy.changgou4.utils.IdWorker;
import com.czxy.changgou4.vo.OrderVo;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Iterator;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Service
@Transactional
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Resource
    private IdWorker idWorker;
    @Resource
    private AddressMapper addressMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private OrderGoodsMapper orderGoodsMapper;
    @Resource
    private SkuFeign skuFeign;

    // 全局事务管理（rollbackFor = 回滚的异常）
    @GlobalTransactional(rollbackFor = Exception.class)
    @Override
    public Long createOrder(User loginUser, OrderVo orderVo) {
        //1 准备订单
        Order order = new Order();
        // 1.1 订单号
        long sn = idWorker.nextId();
        order.setSn(sn);
        // 1.2 所属用户
        order.setUserId(loginUser.getId());
        // 1.3 联系人相关
        System.out.println(orderVo);
        Address address = addressMapper.selectById(orderVo.getAddressId());
//        order.setShrName(address.getShrName());
//        order.setShrMobile(address.getShrMobile());
        //address为null
        BeanUtils.copyProperties(address, order);
        // 1.4 订单状态及其时间
        order.setStatus(CGConstant.ORDER_STATUS_NO_PAY);
        order.setCreatedAt(new Date());
        // 1.5 获得购物车
        String cartKey = "cart" + loginUser.getId();
        String cartJsonStr = stringRedisTemplate.opsForValue().get(cartKey);
        Cart cart = JSON.parseObject(cartJsonStr, Cart.class);
        // 1.6 总价
        order.setTotalPrice(cart.getTotal());
//        添加支付方式
        order.setPayMethod(orderVo.getPayMethod());
        // 2 下订单
        baseMapper.insert(order);

        //3 订单项：购物车购买商品
        Iterator<CartItem> it = cart.getData().values().iterator();
        // 3.1 遍历购物车中商品（勾选）
        while(it.hasNext()) {
            CartItem cartItem = it.next();
            if(cartItem.getChecked()) {
                // 3.2 准备订单项
                OrderGoods orderGoods = new OrderGoods();
                orderGoods.setSn(sn);
                orderGoods.setSkuId(cartItem.getSkuid());
                orderGoods.setSpuId(cartItem.getSpuid());
                orderGoods.setNumber(cartItem.getCount());
                orderGoods.setSpecList(JSON.toJSONString(cartItem.getSpecInfo()));
                orderGoods.setSkuName(cartItem.getGoodsName());
                orderGoods.setLogo(cartItem.getMidlogo());
                orderGoods.setPrice(cartItem.getPrice());

                // 3.3 保存订单项
                orderGoodsMapper.insert(orderGoods);
                // 3.4 从购物车移除当前商品（集合删除）
                it.remove();
                // 3.5 更新购买数量
                skuFeign.updateSkuNum(cartItem.getSkuid(), cartItem.getCount());
            }
        }

        //4 保存购物车
        stringRedisTemplate.opsForValue().set(cartKey, JSON.toJSONString(cart));

        //5 返回订单号
        return sn;
    }

    @Override
    public void updatePayStatus(String sn, Integer status) {
        baseMapper.updateStatus(sn, status);
    }
}
