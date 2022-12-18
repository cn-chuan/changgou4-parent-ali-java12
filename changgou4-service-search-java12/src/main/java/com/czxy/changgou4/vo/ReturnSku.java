package com.czxy.changgou4.vo;

import lombok.Data;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Data
public class ReturnSku {
    private Long id;
    private String goodsName;           //sku名称
    private Double price;               //价格
    private String midlogo;             //sku logo
    private Integer commentCount;      //sku的评论

}
