package com.czxy.changgou4.utils;

import lombok.Getter;

/**
 * 支付状态(枚举类)
 * 自定义支付状态，微信支持多种状态，此处统一四种：
 * SUCCESS—支付成功、NOTPAY—未支付、CLOSED—已关闭、PAYERROR--支付失败
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Getter
public enum PayState {
    //public static final PayState NOTPAY = new PayState(0, "未支付");
    //public static final PayState SUCCESS = new PayState(1, "支付成功");
    //public static final PayState CLOSED = new PayState(2, "已关闭");
    //public static final PayState PAYERROR = new PayState(3, "支付失败");
    // 常量
    NOT_PAY(0,"未支付"),SUCCESS(1,"支付成功"),CLOSED(2,"已关闭"),PAY_ERROR(3,"支付失败");
    // 构造方法
    PayState(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    // 成员变量
    private Integer code;
    private String desc;
}
