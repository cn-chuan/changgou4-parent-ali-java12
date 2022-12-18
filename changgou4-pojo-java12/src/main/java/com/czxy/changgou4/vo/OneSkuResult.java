package com.czxy.changgou4.vo;

import com.czxy.changgou4.pojo.Category;
import com.czxy.changgou4.pojo.Spec;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Data
public class OneSkuResult {

    private Integer skuid;
    private Integer spuid;
    @JsonProperty("goods_name")
    private String goodsName;
    private Double price;
    @JsonProperty("on_sale_date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date onSaleDate;
    @JsonProperty("comment_count")
    private Integer commentCount;
    @JsonProperty("comment_level")
    private Integer commentLevel;
    @JsonProperty("cat1_info")
    private Category cat1Info;
    @JsonProperty("cat2_info")
    private Category cat2Info;
    @JsonProperty("cat3_info")
    private Category cat3Info;
    private Map<String, String> logo;
    private List<Map<String,String>> photos;
    private String description;
    private String aftersale;
    private Integer stock;
    @JsonProperty("spec_list")
    private List<Spec> specList;
    // id_list:'规格ID:选项ID|规格ID:选项ID|...',
    // id_txt:'规格名称:选项名称|规格名称:选项名称|...'
    @JsonProperty("spec_info")
    private Map<String, String> specInfo;
    @JsonProperty("sku_list")
    private List<Map<String, String>> skuList;

}
