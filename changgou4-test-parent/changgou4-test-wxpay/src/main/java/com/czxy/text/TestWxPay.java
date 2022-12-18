package com.czxy.text;

import com.czxy.text.config.MyWXPayConfig;
import com.github.wxpay.sdk.WXPay;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
public class TestWxPay {
    @Test
    public void testUnifiedOrder() throws Exception {
        //1 核心类
        WXPay wxPay = new WXPay(new MyWXPayConfig());
        //2 准备请求参数
        Map<String,String> params = new HashMap<>();
        params.put("body","买了一个手机");                          //商品描述
        params.put("out_trade_no","20221206001");         //订单号
        params.put("total_fee","1");                       //金额
        params.put("spbill_create_ip","127.0.0.1");      //终端IP
        params.put("notify_url","http://www.czxy.com");//回调地址
        //JSAPI--JSAPI支付（或小程序支付）、NATIVE--Native支付、APP--app支付，MWEB--H5支付
        params.put("trade_type","NATIVE");              //交易类型

        //3 下单
        Map<String, String> result = wxPay.unifiedOrder(params);
        //4 处理结果
        System.out.println("支付连接：" + result.get("code_url"));           //   weixin://wxpay/bizpayurl?pr=OI0HotUzz
        System.out.println("返回状态码：" + result.get("return_code"));
        System.out.println("返回信息：" + result.get("return_msg"));
        System.out.println("业务结果：" + result.get("result_code"));
        System.out.println("业务提示信息："+ result.get("err_code_des"));
        System.out.println(result);

    }

    @Test
    public void testOrderQuery() throws Exception {
        //1 核心类
        WXPay wxPay = new WXPay(new MyWXPayConfig());
        //2 请求参数
        Map<String,String> params = new HashMap<>();
        params.put("out_trade_no","20200401001");
        //3 查询
        Map<String, String> result = wxPay.orderQuery(params);
        //4 处理结果
        System.out.println(result);
        System.out.println("返回状态码" + result.get("return_code"));
        System.out.println("返回信息" + result.get("return_msg"));
        System.out.println("业务结果" + result.get("result_code"));
        System.out.println("错误代码" + result.get("err_code"));
        System.out.println("错误代码描述" + result.get("err_code_des"));
    }
}
