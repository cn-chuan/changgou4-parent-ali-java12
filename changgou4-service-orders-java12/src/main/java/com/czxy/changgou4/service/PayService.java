package com.czxy.changgou4.service;

import com.czxy.changgou4.utils.PayState;
import com.czxy.changgou4.vo.PayRequest;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
public interface PayService {

    public String pay(PayRequest payRequest);

    /**
     * 通过订单号查询订单状态
     * @author 桐叔
     * @email liangtong@itcast.cn
     * @return
     */
    public PayState query(Long sn);
}
