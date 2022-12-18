package com.czxy.changgou4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czxy.changgou4.pojo.Impression;
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
public interface ImpressionMapper extends BaseMapper<Impression> {

    @Select("SELECT * FROM tb_impression WHERE spu_id = #{spuId}")
    public List<Impression> findImpressionsBySpuId(@Param("spuId") Integer spuId);
}
