package com.czxy.changgou4.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@TableName("tb_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order  implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    //订单序列号
    @TableField(value ="sn")
    //转换JSON时，将Long转换String，解决精度丢失问题
    @JsonSerialize(using= ToStringSerializer.class)
    private Long sn;
    @TableField(value ="created_at")
    private Date createdAt;
    @TableField(value ="updated_at")
    private Date updatedAt;

    //收货人姓名
    @TableField(value ="shr_name")
    private String shrName;
    //收货人手机
    @TableField(value ="shr_mobile")
    private String shrMobile;
    //收货人省份
    @TableField(value ="shr_province")
    private String shrProvince;
    //收货人城市
    @TableField(value ="shr_city")
    private String shrCity;
    //收货人地区
    @TableField(value ="shr_area")
    private String shrArea;
    //收货人详情地址
    @TableField(value ="shr_address")
    private String shrAddress;

    //订单状态，0:未支付、1:已支付、等待发货、2:已发货、等待收货 3:已收货、等待评论 4:已结束 5:申请售后
    @TableField(value ="status")
    private Integer status;

    //支付时间
    @TableField(value ="pay_time")
    private String payTime;
    //发货时间
    @TableField(value ="post_time")
    private String postTime;
    //用户ID
    @TableField(value ="user_id")
    private Long userId;
    @TableField(exist = false)
    private User user;
    //订单总价
    @TableField(value ="total_price")
    private Double totalPrice;
}
