package com.czxy.changgou4.vo;

import lombok.Data;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Data
public class PageRequest {
    private Integer current;    //当前页
    private Integer size;   	//每页条数
    private Integer limit;      //限制条数
    private Integer offset;     //偏移
    private String sortBy;     //排序字段
    private String sortWay;    //排序方式(asc | desc)

}
