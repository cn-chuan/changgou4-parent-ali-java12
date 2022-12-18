package com.czxy.changgou4.controller;

import com.czxy.changgou4.pojo.Category;
import com.czxy.changgou4.service.CategoryService;
import com.czxy.changgou4.vo.BaseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@RestController
@RequestMapping("/categorys")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping
    public BaseResult findAll() {
        // 查询
        List<Category> list = categoryService.findAll();
        // 返回
        return BaseResult.ok("查询成功", list);
    }
}
