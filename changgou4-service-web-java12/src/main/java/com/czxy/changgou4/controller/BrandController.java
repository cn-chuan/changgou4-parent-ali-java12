package com.czxy.changgou4.controller;

import com.czxy.changgou4.pojo.Brand;
import com.czxy.changgou4.service.BrandService;
import com.czxy.changgou4.vo.BaseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/brands")
public class BrandController {

    @Resource
    private BrandService brandService;

    @GetMapping("/category/{categoryId}")
    public BaseResult findAll(@PathVariable("categoryId") Integer categoryId) {
        // 查询
        List<Brand> list = brandService.findAll(categoryId);
        // 返回
        return BaseResult.ok("查询成功", list);
    }
}
