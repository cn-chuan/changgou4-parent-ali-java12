package com.czxy.changgou4.service;

import com.czxy.changgou4.vo.SearchVo;

import java.util.Map;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
public interface SkuSearchService {
    Map search(SearchVo searchVo);
}
