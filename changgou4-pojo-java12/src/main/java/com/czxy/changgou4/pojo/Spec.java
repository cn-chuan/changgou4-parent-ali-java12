package com.czxy.changgou4.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@TableName("tb_specification")
@Data
public class Spec {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("spec_name")
    private String specName;

    @TableField("category_id")
    private Integer categoryId;

    // 一对多关系，一个规格拥有多个规格选项
    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<SpecOption> options = new ArrayList<>();
}
/*
CREATE TABLE `tb_specification` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `spec_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '规格名称',
  `category_id` int(10) unsigned NOT NULL COMMENT '分类ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci

 */