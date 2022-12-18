package com.czxy.changgou4.vo;

import lombok.Data;

import java.util.Map;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Data
public class SearchVo extends PageRequest {

    private String keyword;                 // 关键字搜索，预留
    private Integer catId;                  // 3 级类目
    private Integer brandId;                // 品牌
    private Map<String,String> specList;    // 规格选项列表
    private Double minPrice;                //最低价格
    private Double maxPrice;                //最高价格

}
