package com.czxy.changgou4.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@TableName("tb_sku_comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkuComment {

    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField(value="created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createdAt;
    @TableField(value="updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updatedAt;

    @TableField(value="user_id")
    private Integer userId;
    @TableField(exist = false)
    private User user;

    @TableField(value="spu_id")
    private Integer spuId;
    @TableField(exist = false)
    private Spu spu;

    @TableField(value="sku_id")
    private Integer skuId;
    @TableField(exist = false)
    private Sku sku;

    @TableField(value="ratio")
    private String ratio;

    @TableField(value="spec_list")
    private String specList;


    @TableField(value="content")
    private String content;
    @TableField(value="star")
    private Integer star;
    @TableField(value="isshow")
    private String isShow;

    @TableField(value="sn")
    private String sn;

}
