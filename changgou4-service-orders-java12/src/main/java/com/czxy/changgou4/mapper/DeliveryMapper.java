package com.czxy.changgou4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czxy.changgou4.pojo.Delivery;
import org.apache.ibatis.annotations.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Mapper
public interface DeliveryMapper extends BaseMapper<Delivery> {
    @Select("select * from tb_delivery")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "price",column = "price"),
            @Result(property = "priceNorm",column = "priceNorm"),
            @Result(property = "users",column = "users"),
            @Result(property = "list",column = "id",many = @Many(select ="com.czxy.changgou4.mapper.DeliveryTimeMapper.findAll" )),
    })
    public ArrayList<Delivery> findAll();

}
