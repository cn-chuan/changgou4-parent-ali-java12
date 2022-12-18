package com.czxy.changgou4.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Data
public class OrderVo {

    //收货人地址ID
    @JsonProperty("address_id")
    private Integer addressId;

    //送货方式
    @JsonProperty("post_method")
    private Integer postMethod;

    //支付方式
    @JsonProperty("pay_method")
    private Integer payMethod;

    //发票
    private Map<Object,Object> invoice;
}
