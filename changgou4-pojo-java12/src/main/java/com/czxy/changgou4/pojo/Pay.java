package com.czxy.changgou4.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_pay")
public class Pay {
    @TableId
    private Integer id;
    private String name;
    @TableField("`desc`")
    private String desc;
    private Integer fid;
}
