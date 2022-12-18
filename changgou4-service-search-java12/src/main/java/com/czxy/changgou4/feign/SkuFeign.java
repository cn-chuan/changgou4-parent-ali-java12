package com.czxy.changgou4.feign;

import com.czxy.changgou4.vo.BaseResult;
import com.czxy.changgou4.vo.SearchSku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@FeignClient(value = "web-service", path = "/sku")
public interface SkuFeign {

    @GetMapping("/esData")
    public BaseResult<List<SearchSku>> findESData();
}
