package com.czxy.changgou4.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czxy.changgou4.mapper.NewsMapper;
import com.czxy.changgou4.pojo.News;
import com.czxy.changgou4.service.NewsService;
import com.czxy.changgou4.vo.NewsVo;
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
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {
    @Override
    public Page<News> findAll(NewsVo newsVo) {
        //1 条件
        QueryWrapper<News> queryWrapper = new QueryWrapper();
        if("desc".equalsIgnoreCase(newsVo.getSortWay())) {
            queryWrapper.orderByDesc("created_at");
        } else {
            queryWrapper.orderByAsc("created_at");
        }

        //2 分页
        Page<News> page = new Page<>(newsVo.getCurrent(), newsVo.getSize());
        //3 查询
        baseMapper.selectPage(page, queryWrapper);
        //4 返回
        return page;
    }
}
