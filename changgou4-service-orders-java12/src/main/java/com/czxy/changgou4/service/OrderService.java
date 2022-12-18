package com.czxy.changgou4.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.czxy.changgou4.pojo.Order;
import com.czxy.changgou4.pojo.User;
import com.czxy.changgou4.vo.OrderVo;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
public interface OrderService extends IService<Order> {

    /**
     * 下订单
     * @param loginUser 登录用户
     * @param orderVo 请求参数
     * @return 订单号
     */
    public Long createOrder(User loginUser, OrderVo orderVo);

    /**
     * 根据订单号修改订单状态
     * @author 桐叔
     * @email liangtong@itcast.cn
     * @return
     */
    void updatePayStatus(String sn, Integer status);
}
