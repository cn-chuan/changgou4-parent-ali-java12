package com.czxy.changgou4.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
@TableName("tb_delivery_time")
@Data
public class DeliveryTime {
    @TableId
    private Integer id;
    private String name;
    private Integer did;
    private boolean isdefault;
}
