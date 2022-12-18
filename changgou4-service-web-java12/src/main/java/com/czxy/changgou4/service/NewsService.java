package com.czxy.changgou4.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.czxy.changgou4.pojo.News;
import com.czxy.changgou4.vo.NewsVo;

import java.util.List;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
public interface NewsService extends IService<News> {
    public Page<News> findAll(NewsVo newsVo);
}
