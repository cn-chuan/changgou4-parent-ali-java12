package com.czxy.changgou4.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.czxy.changgou4.pojo.Spec;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
public interface SpecService extends IService<Spec> {

    public List<Spec> findAllByCatId(Integer categoryId);
}
