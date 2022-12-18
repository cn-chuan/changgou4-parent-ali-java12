package com.czxy.changgou4.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@TableName("tb_order_good")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderGoods implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(value ="sn")
    private Long sn;
    @TableField(exist = false)
    private Order order;

    @TableField(value ="sku_id")
    private Integer skuId;
    @TableField(exist = false)
    private Sku sku;

    @TableField(value ="spu_id")
    private Integer spuId;

    //购买数量
    @TableField(value ="number")
    private Integer number;
    //规格列表
    @TableField(value ="spec_list")
    private String specList;
    //商品名称
    @TableField(value ="sku_name")
    private String skuName;
    @TableField(value ="url")
    private String logo;
    //价格
    @TableField(value ="price")
    private Double price;
}

