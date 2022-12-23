package com.czxy.changgou4.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czxy.changgou4.mapper.PayMapper;
import com.czxy.changgou4.pojo.Pay;
import com.czxy.changgou4.service.PayMethodService;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class PayMethodServiceImpl extends ServiceImpl<PayMapper, Pay> implements PayMethodService {

    @Override
    public List<Pay> getByFid(Integer id) {
        QueryWrapper<Pay> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("fid",id);
        return baseMapper.selectList(queryWrapper);
    }
}
