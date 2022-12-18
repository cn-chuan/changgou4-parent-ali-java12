package com.czxy.changgou4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czxy.changgou4.pojo.DeliveryTime;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface DeliveryTimeMapper extends BaseMapper<DeliveryTime> {
    @Select("select * from tb_delivery_time where did= #{id};")
    public ArrayList<DeliveryTime> findAll(Integer id);
}
