package com.czxy.changgou4.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@TableName("tb_address")
@Data
public class Address {

    @TableId(type = IdType.AUTO)
    private Integer id;
    //用户ID
    @TableField(value = "user_id")
    private Long userId;
    //收货人姓名
    @TableField(value = "shr_name")
    @JsonProperty("shr_name")
    private String shrName;
    //收货人手机
    @TableField(value = "shr_mobile")
    @JsonProperty("shr_mobile")
    private String shrMobile;
    //收货人省份
    @TableField(value = "shr_province")
    @JsonProperty("shr_province")
    private String shrProvince;
    //收货人城市
    @TableField(value = "shr_city")
    @JsonProperty("shr_city")
    private String shrCity;
    //收货人地区
    @TableField(value = "shr_area")
    @JsonProperty("shr_area")
    private String shrArea;
    //收货人详情地址
    @TableField(value = "shr_address")
    @JsonProperty("shr_address")
    private String shrAddress;
    //1:默认;0:不是
    @TableField(value = "isdefault")
    @JsonProperty("isdefault")
    private Integer isdefault;

}

