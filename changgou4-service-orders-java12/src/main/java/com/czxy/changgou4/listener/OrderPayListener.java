package com.czxy.changgou4.listener;

import com.czxy.changgou4.config.OrderPayQueue;
import com.czxy.changgou4.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Component
@RabbitListener(queues = OrderPayQueue.ORDER_PAY_QUEUE)
public class OrderPayListener {

    @Resource
    private OrderService orderService;

    @RabbitHandler
    public void updatePayStatus(String sn) {
        System.out.println("MQ消费：" + sn);
        // 根据订单号，修改订单状态
        orderService.updatePayStatus(sn, 1);
    }
}
