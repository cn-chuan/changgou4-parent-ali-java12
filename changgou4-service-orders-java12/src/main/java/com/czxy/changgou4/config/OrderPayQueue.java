package com.czxy.changgou4.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Component
public class OrderPayQueue {
    public static final String ORDER_PAY_QUEUE = "order_pay";
    public static final String ORDER_PAY_AUTO_QUEUE = "order_pay_auto";

    @Bean(ORDER_PAY_QUEUE)
    public Queue queue() {
        return new Queue(ORDER_PAY_QUEUE);
    }

    @Bean(ORDER_PAY_AUTO_QUEUE)
    public Queue queueAuto() {
        return new Queue(ORDER_PAY_AUTO_QUEUE);
    }
}
