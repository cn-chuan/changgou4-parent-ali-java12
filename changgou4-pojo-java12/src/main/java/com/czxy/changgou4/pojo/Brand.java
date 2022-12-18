package com.czxy.changgou4.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@TableName("tb_brand")
@Data
public class Brand {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("brand_name")
    private String brandName;

    @TableField("logo")
    private  String logo;
}

/*

Create Table

CREATE TABLE `tb_brand` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '品牌id',
  `brand_name` varchar(50) NOT NULL COMMENT '品牌名称',
  `logo` varchar(200) DEFAULT '' COMMENT '品牌图片地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=325403 DEFAULT CHARSET=utf8 COMMENT='品牌表，一个品牌下有多个商品（spu），一对多关系'
 */