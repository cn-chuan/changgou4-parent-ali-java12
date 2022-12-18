package com.czxy.changgou4.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Data
public class CartSpec {
    private Integer id;

    private String specName;

    private Integer categoryId;

    // 一对多关系，一个规格拥有多个规格选项
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CartSpecOption> options = new ArrayList<>();
}
