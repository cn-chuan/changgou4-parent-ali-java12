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
@TableName("tb_specification_option")
@Data
public class SpecOption {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("spec_id")
    private Integer specId;

    @TableField("option_name")
    private String optionName;
}

/*

Create Table

CREATE TABLE `tb_specification_option` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `spec_id` int(10) unsigned NOT NULL COMMENT '规格ID',
  `option_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '选项名称',
  PRIMARY KEY (`id`),
  KEY `specification_options_spec_id_index` (`spec_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8
 */