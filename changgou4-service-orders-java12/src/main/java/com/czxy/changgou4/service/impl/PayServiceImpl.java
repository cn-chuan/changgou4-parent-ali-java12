package com.czxy.changgou4.service.impl;

import com.czxy.changgou4.service.PayService;
import com.czxy.changgou4.utils.PayHelper;
import com.czxy.changgou4.utils.PayState;
import com.czxy.changgou4.vo.PayRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Service
public class PayServiceImpl implements PayService {
    @Resource
    private PayHelper payHelper;
    @Override
    public String pay(PayRequest payRequest) {
        String payUrl = payHelper.createPayUrl(payRequest.getSn());

        return payUrl;
    }

    @Override
    public PayState query(Long sn) {
        PayState payState = payHelper.queryOrder(sn);
        return payState;
    }
}
