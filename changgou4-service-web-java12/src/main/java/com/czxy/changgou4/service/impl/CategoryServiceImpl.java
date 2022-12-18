package com.czxy.changgou4.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czxy.changgou4.mapper.CategoryMapper;
import com.czxy.changgou4.pojo.Category;
import com.czxy.changgou4.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Service
@Transactional
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Override
    public List<Category> findAll() {
        //1 查询所有，parent_id 升序排序
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("parent_id");
        List<Category> list = baseMapper.selectList(queryWrapper);

        //2 遍历所有的分类，并处理
        // 2.1 存储：List处理后的数据、Map缓存当前数据给孩子使用
        List<Category> resultList = new ArrayList<>();
        Map<Integer, Category> cache = new HashMap<>();

        for (Category category : list) {
            // 2.2 从map缓存获得
            Category parentCategory = cache.get(category.getParentId());

            // 2.3.1 如果有父分类，将当前分类追加到父分类中
            if(parentCategory != null) {
                parentCategory.getChildren().add(category);
            } else {
                // 2.3.2 如果没有，就是父分类，添加到List中
                // 1) 如果parent_id = 0，添加List中
                if(category.getParentId() == 0) {
                    resultList.add(category);
                } else {
                    // 2) 如果 !=0 查询一次【补充】
                    parentCategory = baseMapper.selectById(category.getParentId());
                    if(parentCategory != null) {
                        parentCategory.getChildren().add(category);
                    }
                }
            }

            // 2.4 将当前的分类添加到map
            cache.put(category.getId(), category);

        }

        // 3. 返回
        return resultList;
    }
}
