package com.czxy.changgou4.controller;

import com.czxy.changgou4.service.SkuService;
import com.czxy.changgou4.vo.BaseResult;
import com.czxy.changgou4.vo.ESData;
import com.czxy.changgou4.vo.OneSkuResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.sql.rowset.BaseRowSet;
import java.util.List;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@RestController
@RequestMapping("/sku")
public class SkuController {

    @Resource
    private SkuService skuService;

    @GetMapping("/esData")
    public BaseResult<List<ESData>> findESData() {
        // 查询
        List<ESData> esDataList = skuService.findESData();

        // 返回
        return BaseResult.ok("查询成功", esDataList);
    }

    @GetMapping("/goods/{skuId}")
    public BaseResult<OneSkuResult> findSkuById(@PathVariable("skuId") Integer skuId ) {
        // 查询
        OneSkuResult oneSkuResult = skuService.findSkuById(skuId);
        // 返回
        return BaseResult.ok("查询成功", oneSkuResult);
    }


    @PutMapping("/goods/{skuId}")
    public BaseResult updateSkuNum(@PathVariable("skuId") Integer skuId, @RequestParam("count") Integer count) {
        // 修改
        skuService.updateSkuNum(skuId, count);
        // 返回
        return BaseResult.ok("更新成功");
    }
}
