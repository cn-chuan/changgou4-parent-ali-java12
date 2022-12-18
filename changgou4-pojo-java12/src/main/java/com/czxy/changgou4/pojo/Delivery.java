package com.czxy.changgou4.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.util.ArrayList;

@Data
@TableName("tb_delivery")
public class Delivery {
    private Integer id;
    private String name;
    private double price;
    private String priceNorm;
    private boolean isdefault;
    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private ArrayList<DeliveryTime> list=new ArrayList<DeliveryTime>();
}
