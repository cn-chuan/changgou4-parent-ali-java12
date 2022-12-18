package com.czxy.changgou4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czxy.changgou4.pojo.Spec;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Mapper
public interface SpecMapper extends BaseMapper<Spec> {
    /**
     * 查询指定分类的所有规格
     * @author 桐叔
     * @email liangtong@itcast.cn
     * @return
     */
    @Select("SELECT * FROM tb_specification WHERE category_id = #{categoryId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "specName", column = "spec_name"),
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "options",
                    many = @Many(select = "com.czxy.changgou4.mapper.SpecOptionMapper.findAllBySpecId"),
                    column = "id"),
    })
    public List<Spec> findAllByCatId(@Param("categoryId") Integer categoryId);
}
