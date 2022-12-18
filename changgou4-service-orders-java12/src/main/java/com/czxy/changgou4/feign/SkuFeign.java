package com.czxy.changgou4.feign;

import com.czxy.changgou4.vo.BaseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@FeignClient(value = "web-service", path = "/sku")
public interface SkuFeign {

    @PutMapping("/goods/{skuId}")
    public BaseResult updateSkuNum(@PathVariable("skuId") Integer skuId, @RequestParam("count") Integer count);

}