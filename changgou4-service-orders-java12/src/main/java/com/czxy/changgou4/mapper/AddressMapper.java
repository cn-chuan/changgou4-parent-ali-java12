package com.czxy.changgou4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czxy.changgou4.pojo.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Mapper
public interface AddressMapper extends BaseMapper<Address> {

    /**
     * 通过指定用户查询所有的地址
     * @author 桐叔
     * @email liangtong@itcast.cn
     * @return
     */
    @Select("SELECT * FROM tb_address WHERE user_id = #{userId}")
    public List<Address> findAllByUserId(@Param("userId") Long userId);

    /**
     * 更新指定用户的默认值
     * @author 桐叔
     * @email liangtong@itcast.cn
     * @return
     */
    @Update("UPDATE tb_address SET isdefault = #{isdefault} WHERE user_id = #{userId}")
    public void updateDefault(@Param("isdefault") Integer isdefault, @Param("userId") Long userId );
}
