package com.czxy.changgou4.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@TableName("tb_spu")
@Data
public class Spu {

    @TableId(type = IdType.AUTO)
    private Integer id;

    //spu名字
    @TableField(value="spu_name")
    private String spuName;
    //spu副名称
    @TableField(value="spu_subname")
    private String spuSubname;
    //商品logo
    @TableField(value="logo")
    private String logo;
    //分类1Id
    @TableField(value="cat1_id")
    private Integer cat1Id;
    //分类2ID
    @TableField(value="cat2_id")
    private Integer cat2Id;
    //分类3Id
    @TableField(value="cat3_id")
    private Integer cat3Id;

    @TableField(value="brand_id")
    private Integer brandId;
    @TableField(exist = false)
    private Brand brand;
    //审核时间
    @TableField(value="check_time")
    private String checkTime;
    //审核状态 审核状态，0：未审核，1：已通过，2：未通过
    @TableField(value="check_status")
    private String checkStatus;
    //价格
    @TableField(value="price")
    private String price;
    //是否上架
    @TableField(value="is_on_sale")
    private Integer isOnSale;
    //上架时间
    @TableField(value="on_sale_time")
    private Date onSaleTime;
    //删除时间
    @TableField(value="deleted_at")
    private String deletedAt;

    @TableField(value="weight")
    private String weight;

    //商品描述
    @TableField(value="description")
    private String description;
    //规格与包装
    @TableField(value="packages")
    private String packages;
    //售后保障
    @TableField(value="aftersale")
    private String aftersale;
    //规格列表，json串
    @TableField(value="spec_list")
    private String specList;

    @TableField(value="created_at")
    private String createdAt;
    @TableField(value="updated_at")
    private String updatedAt;


}

