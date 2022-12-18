package com.czxy.changgou4.utils;

import lombok.Getter;

/**
 * 常量类
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Getter
public class PayStateClass {
    public static final PayStateClass NOTPAY = new PayStateClass(0, "未支付");
    public static final PayStateClass SUCCESS = new PayStateClass(1, "支付成功");
    public static final PayStateClass CLOSED = new PayStateClass(2, "已关闭");
    public static final PayStateClass PAYERROR = new PayStateClass(3, "支付失败");

    private Integer code;
    private String desc;

    public PayStateClass(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
