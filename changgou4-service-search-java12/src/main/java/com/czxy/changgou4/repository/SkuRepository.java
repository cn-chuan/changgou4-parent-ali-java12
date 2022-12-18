package com.czxy.changgou4.repository;

import com.czxy.changgou4.vo.SearchSku;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
public interface SkuRepository extends ElasticsearchRepository<SearchSku, Long> {
}
