package com.czxy.changgou4.controller;

import com.czxy.changgou4.config.OrderPayQueue;
import com.czxy.changgou4.service.PayService;
import com.czxy.changgou4.utils.PayState;
import com.czxy.changgou4.vo.BaseResult;
import com.czxy.changgou4.vo.PayRequest;
import com.github.wxpay.sdk.WXPayUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@RestController
@RequestMapping("/pay")
public class PayController {

    @Resource
    private PayService payService;
    @Resource
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public BaseResult pay(@RequestBody PayRequest payRequest) {
        // 获得支付链接
        String payUrl = payService.pay(payRequest);

        if(payUrl != null) {
            // 返回
            return BaseResult.ok("生成二维码成功").append("wxurl", payUrl);
        }
        return BaseResult.error("生成二维码失败");
    }

    @PostMapping("/callback")
    public void callback(HttpServletRequest request , HttpServletResponse response) throws IOException {
        try {
            //1 获得响应内容
            String xml = IOUtils.toString(request.getInputStream());
            System.out.println(xml);
            //2 解析
            Map<String, String> map = WXPayUtil.xmlToMap(xml);
            //3 如果成功，获得订单号
            if("SUCCESS".equals(map.get("return_code"))) {
                String sn = map.get("out_trade_no");
                //4 将订单号存放到MQ中
                System.out.println(sn);
                // 4.1 修改订单状态
                rabbitTemplate.convertAndSend("", OrderPayQueue.ORDER_PAY_QUEUE, sn);
                // 4.2 消息推送
                rabbitTemplate.convertAndSend("", OrderPayQueue.ORDER_PAY_AUTO_QUEUE, sn);
                //5 给微信一个成功的响应
                String data = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
                response.setContentType("text/xml;charset=UTF-8");
                response.getWriter().write(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            String data = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA["+e.getMessage()+"]]></return_msg></xml>";
            response.setContentType("text/xml;charset=UTF-8");
            response.getWriter().write(data);
        }
    }

    @GetMapping("/{sn}")
    public BaseResult query(@PathVariable("sn") Long sn) {
        // 查询
        PayState payState = payService.query(sn);
        // 返回
        if(payState.getCode() == 0) {
            return BaseResult.error(payState.getDesc());
        }
        return BaseResult.ok(payState.getDesc());
    }

}
