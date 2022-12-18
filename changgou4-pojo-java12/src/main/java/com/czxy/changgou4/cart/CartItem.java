package com.czxy.changgou4.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Data
public class CartItem {
    private Integer skuid;
    private Integer spuid;
    @JsonProperty("goods_name")
    private String goodsName;
    private Double price;
    private Integer count;          // 购买数量
    private Boolean checked;        // 是否选中
    private String midlogo;
    @JsonProperty("spec_info")
    private Map<String, String> specInfo;

}
