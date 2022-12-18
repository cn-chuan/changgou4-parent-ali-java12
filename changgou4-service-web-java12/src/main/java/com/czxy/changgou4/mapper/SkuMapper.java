package com.czxy.changgou4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czxy.changgou4.pojo.Sku;
import com.czxy.changgou4.pojo.SkuPhoto;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Mapper
public interface SkuMapper extends BaseMapper<Sku> {

    @Select("SELECT * FROM tb_sku")
    @Results(id = "skuResultMap" , value = {
        @Result(property = "id",column = "id"),
        @Result(property = "stock",column = "stock"),
        @Result(column="spu_id",property="spuId"),
        @Result(column="sku_name",property="skuName"),
        @Result(column="spec_info_id_list",property="specInfoIdList"),
        @Result(column="spec_info_id_txt",property="specInfoIdTxt"),
        @Result(property = "spu", column = "spu_id",
                one = @One(select = "com.czxy.changgou4.mapper.SpuMapper.findSpuById"))
    })
    public List<Sku> findAllSkus();

    @Select("SELECT * FROM tb_sku WHERE spu_id = #{spuId}")
    @ResultMap("skuResultMap")
    public List<Sku> findSkuBySpuId(@Param("spuId") Integer spuId);
}
