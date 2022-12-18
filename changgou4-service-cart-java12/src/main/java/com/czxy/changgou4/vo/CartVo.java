package com.czxy.changgou4.vo;

import lombok.Data;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Data
public class CartVo {
    private Integer skuid ;     //"SKUID",
    private Integer count;      //"购买数量"
    private Boolean checked;    //"是否选中"

}
