package com.czxy.changgou4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czxy.changgou4.pojo.Spu;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Mapper
public interface SpuMapper extends BaseMapper<Spu> {

    @Select("SELECT * FROM tb_spu WHERE id = #{spuId}")
    @Results({
        @Result(property = "spuName" , column = "spu_name"),
        @Result(property = "spuSubname" , column = "spu_subname"),
        @Result(property = "cat1Id" , column = "cat1_id"),
        @Result(property = "cat2Id" , column = "cat2_id"),
        @Result(property = "cat3Id" , column = "cat3_id"),
        @Result(property = "brandId" , column = "brand_id"),
        @Result(property = "checkTime" , column = "check_time"),
        @Result(property = "checkStatus" , column = "check_status"),
        @Result(property = "isOnSale" , column = "is_on_sale"),
        @Result(property = "onSaleTime" , column = "on_sale_time"),
        @Result(property = "deletedAt" , column = "deleted_at"),
        @Result(property = "specList" , column = "spec_list"),
        @Result(property = "createdAt" , column = "created_at"),
        @Result(property = "updatedAt" , column = "updated_at"),
        @Result(property = "brand", column = "brand_id",
                one = @One(select = "com.czxy.changgou4.mapper.BrandMapper.selectById"))
    })
    public Spu findSpuById(@Param("spuId") Integer spuId);
}
