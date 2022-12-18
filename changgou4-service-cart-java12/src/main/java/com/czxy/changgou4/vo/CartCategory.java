package com.czxy.changgou4.vo;

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
@Data
public class CartCategory {
    private Integer id;

    @JsonProperty("cat_name")
    private String catName;

    @JsonProperty("parent_id")
    private Integer parentId;

    @JsonProperty("is_parent")
    private Boolean isParent;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CartCategory> children = new ArrayList<>();

}
