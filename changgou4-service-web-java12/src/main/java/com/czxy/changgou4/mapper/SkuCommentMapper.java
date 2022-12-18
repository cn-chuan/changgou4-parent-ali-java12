package com.czxy.changgou4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czxy.changgou4.pojo.Sku;
import com.czxy.changgou4.pojo.SkuComment;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Mapper
public interface SkuCommentMapper extends BaseMapper<SkuComment> {

    @Select("SELECT COUNT(*) FROM tb_sku_comment WHERE spu_id = #{spuId}")
    public Integer  findNumBySpuId(@Param("spuId") Integer spuId);

    @Select("SELECT AVG(star) FROM tb_sku_comment WHERE spu_id = #{spuId}")
    public Integer findAvgStarBySpuId(@Param("spuId") Integer spuId);

    @Select("SELECT COUNT(*) FROM tb_sku_comment WHERE spu_id = #{spuId} AND ratio = #{ratio}")
    public Integer findCommentCountByRatio(@Param("spuId") Integer spuId, @Param("ratio") Integer ratio);

    @Select("SELECT COUNT(*) FROM tb_sku_comment WHERE spu_id = #{spuId}")
    public Integer findTotalCommentBySpuId(@Param("spuId") Integer spuId);

    @Select("SELECT * FROM tb_sku_comment WHERE spu_id = #{spuId} LIMIT #{startIndex}, #{size}")
    @Results({
            @Result(property = "user", column = "user_id" , one = @One(select = "com.czxy.changgou4.mapper.UserMapper.selectById"))
    })
    public List<SkuComment> findCommentsBySpuId(
            @Param("spuId") Integer spuId,
            @Param("startIndex") Integer startIndex,
            @Param("size") Integer size);
}
