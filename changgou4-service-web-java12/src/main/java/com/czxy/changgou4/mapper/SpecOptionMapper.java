package com.czxy.changgou4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czxy.changgou4.pojo.SpecOption;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Mapper
public interface SpecOptionMapper extends BaseMapper<SpecOption> {
    /**
     * 查询指定规格的所有规格选项
     * @author 桐叔
     * @email liangtong@itcast.cn
     * @return
     */
    @Select("SELECT * FROM tb_specification_option WHERE spec_id = #{specId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "specId", column = "spec_id"),
            @Result(property = "optionName", column = "option_name"),
    })
    public List<SpecOption> findAllBySpecId(@Param("specId") Integer specId);
}
