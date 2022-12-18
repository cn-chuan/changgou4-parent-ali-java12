package com.czxy.changgou4.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_delivery_user")
public class DeliveryUser {
    @TableId
    private Long uid;
    private Integer did;
    private Integer tid;
}
