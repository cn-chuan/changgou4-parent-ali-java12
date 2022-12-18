package com.czxy.changgou4.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czxy.changgou4.mapper.SpecMapper;
import com.czxy.changgou4.pojo.Spec;
import com.czxy.changgou4.service.SpecService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Service
@Transactional
public class SpecServiceImpl extends ServiceImpl<SpecMapper, Spec> implements SpecService {
    @Override
    public List<Spec> findAllByCatId(Integer categoryId) {
        return baseMapper.findAllByCatId(categoryId);
    }
}
