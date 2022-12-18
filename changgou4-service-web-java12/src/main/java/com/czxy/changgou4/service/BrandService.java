package com.czxy.changgou4.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.czxy.changgou4.pojo.Brand;

import java.util.List;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
public interface BrandService extends IService<Brand> {

    public List<Brand> findAll(Integer categoryId);
}
