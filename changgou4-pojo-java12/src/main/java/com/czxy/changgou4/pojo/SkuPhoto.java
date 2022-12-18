package com.czxy.changgou4.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@TableName("tb_sku_photo")
@Data
public class SkuPhoto {

    @TableId(type = IdType.AUTO)
    private Integer id;
    //外键
    @TableField(value="sku_id")
    @JsonProperty("sku_id")
    private Integer skuId;
    @TableField(exist = false)
    private Sku sku;
    @TableField(value="url")
    private String url;

}
