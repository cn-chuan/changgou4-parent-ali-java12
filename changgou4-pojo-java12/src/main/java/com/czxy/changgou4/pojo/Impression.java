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
@TableName("tb_impression")
@Data
public class Impression {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String title;
    private Integer count;

    @TableField("spu_id")
    @JsonProperty("spu_id")
    private Integer spuId;

    @TableField("sku_id")
    @JsonProperty("sku_id")
    private Integer skuId;

}
