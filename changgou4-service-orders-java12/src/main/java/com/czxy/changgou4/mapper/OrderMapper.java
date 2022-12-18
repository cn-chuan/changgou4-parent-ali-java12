package com.czxy.changgou4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czxy.changgou4.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    @Update("UPDATE tb_order SET STATUS = #{status} WHERE sn = #{sn}")
    void updateStatus(@Param("sn") String sn, @Param("status") Integer status);
}
