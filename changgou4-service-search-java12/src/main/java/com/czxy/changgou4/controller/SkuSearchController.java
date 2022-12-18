package com.czxy.changgou4.controller;

import com.czxy.changgou4.service.SkuSearchService;
import com.czxy.changgou4.vo.BaseResult;
import com.czxy.changgou4.vo.SearchVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@RestController
@RequestMapping("/sku")
public class SkuSearchController {

    @Resource
    private SkuSearchService skuSearchService;

    @PostMapping("/search")
    public BaseResult findAllSkus(@RequestBody SearchVo searchVo) {
        // 参数校验
        if(searchVo.getCatId() == null) {
            return BaseResult.error("缺失分类ID");
        }
        if(searchVo.getCurrent() == null || searchVo.getSize() == null) {
            return BaseResult.error("缺失分页参数");
        }
        // 查询
        Map resultMap = skuSearchService.search(searchVo);

        // 返回
        return BaseResult.ok("查询成功", resultMap);
    }
}
