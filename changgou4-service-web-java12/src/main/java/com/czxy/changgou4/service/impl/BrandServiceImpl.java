package com.czxy.changgou4.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czxy.changgou4.mapper.BrandMapper;
import com.czxy.changgou4.pojo.Brand;
import com.czxy.changgou4.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {
    @Override
    public List<Brand> findAll(Integer categoryId) {
        return baseMapper.findAll(categoryId);
    }
}
