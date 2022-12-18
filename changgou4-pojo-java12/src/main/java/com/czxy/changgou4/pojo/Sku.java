package com.czxy.changgou4.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@TableName("tb_sku")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sku {

    @TableId(type = IdType.AUTO)
    private Integer id;
    //库存量
    @TableField(value="stock")
    private Integer stock;

    @TableField(value="spu_id")
    private Integer spuId;
    @TableField(exist = false)
    private Spu spu;
    //sku名字
    @TableField(value="sku_name")
    private String skuName;

    @TableField(value="images")
    private String images;
    @TableField(value="price")
    private Double price;

    //1:1|2:6|6:22
    @TableField(value="spec_info_id_list")
    private String specInfoIdList;
    //规格列表码,格式：{"机身颜色":"白色","内存":"3GB","机身存储":"16GB"}
    @TableField(value="spec_info_id_txt")
    private String specInfoIdTxt;

    @TableField(value="created_at")
    private String createdAt;
    @TableField(value="updated_at")
    private String updatedAt;


}

