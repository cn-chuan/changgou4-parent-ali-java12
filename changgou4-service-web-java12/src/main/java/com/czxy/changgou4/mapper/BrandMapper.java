package com.czxy.changgou4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czxy.changgou4.pojo.Brand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Mapper
public interface BrandMapper extends BaseMapper<Brand> {

    @Select("SELECT b.* FROM tb_brand b, tb_category_brand cb WHERE b.id = cb.brand_id AND cb.category_id = #{categoryId}")
    public List<Brand> findAll(@Param("categoryId") Integer categoryId);
}
