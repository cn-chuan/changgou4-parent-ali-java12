package com.czxy.changgou4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czxy.changgou4.pojo.SkuPhoto;
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
public interface SkuPhotoMapper extends BaseMapper<SkuPhoto> {

    @Select("SELECT * FROM tb_sku_photo WHERE sku_id = #{skuId}")
    public List<SkuPhoto> findSkuPhotoBySkuId(@Param("skuId") Integer skuId);
}
