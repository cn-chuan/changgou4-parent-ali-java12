package com.czxy.changgou4.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.czxy.changgou4.pojo.Category;

import java.util.List;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
public interface CategoryService extends IService<Category> {
    /**
     * 查询所有的分类（含子分类）
     * @author 桐叔
     * @email liangtong@itcast.cn
     * @return
     */
    public List<Category> findAll();
}
