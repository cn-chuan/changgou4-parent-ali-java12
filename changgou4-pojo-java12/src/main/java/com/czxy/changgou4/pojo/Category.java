package com.czxy.changgou4.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@TableName("tb_category")
@Data
public class Category {
    @TableId(type = IdType.INPUT)
    private Integer id;

    @TableField("cat_name")
    @JsonProperty("cat_name")
    private String catName;

    @TableField("parent_id")
    @JsonProperty("parent_id")
    private Integer parentId;

    @TableField("is_parent")
    @JsonProperty("is_parent")
    private Boolean isParent;

    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Category> children = new ArrayList<>();

}

/*
CREATE TABLE `tb_category` (
  `id` bigint(20) NOT NULL,
  `cat_name` varchar(20) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `is_parent` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

 */