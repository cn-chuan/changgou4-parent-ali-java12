package com.czxy.changgou4.service.impl;

import com.czxy.changgou4.repository.SkuRepository;
import com.czxy.changgou4.service.SkuSearchService;
import com.czxy.changgou4.vo.ReturnSku;
import com.czxy.changgou4.vo.SearchSku;
import com.czxy.changgou4.vo.SearchVo;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class SkuSearchServiceImpl implements SkuSearchService {
    @Resource
    private SkuRepository skuRepository;
    @Override
    public Map search(SearchVo searchVo) {
        //1 多条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 1.1 分类，必须有
        boolQueryBuilder.must(QueryBuilders.termQuery("catId", searchVo.getCatId()));
        // 1.2 品牌
        if(searchVo.getBrandId() != null) {
            boolQueryBuilder.must(QueryBuilders.termQuery("brandId", searchVo.getBrandId()));
        }
        // 1.3 规格
        if(searchVo.getSpecList() != null) {
            for (Map.Entry<String, String> specEntry : searchVo.getSpecList().entrySet()) {
                boolQueryBuilder.must(QueryBuilders.termQuery("specs."+specEntry.getKey()+".keyword", specEntry.getValue()));
            }
        }
        // 1.4 关键字
        if(StringUtils.isNotBlank(searchVo.getKeyword())) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("skuName", searchVo.getKeyword()));
        }
        // 1.5 价格范围 (将元转成成分)
        if(searchVo.getMinPrice() != null) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("price").gte( searchVo.getMinPrice() * 100));
        }
        if(searchVo.getMaxPrice() != null) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("price").lte(searchVo.getMaxPrice() * 100));
        }

        //2 核心构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 2.1 查询条件
        queryBuilder.withQuery(boolQueryBuilder);
        // 2.2 分页 (页面page从0开始)
        queryBuilder.withPageable(PageRequest.of(searchVo.getCurrent()-1, searchVo.getSize()));
        // 2.3 排序
        /*
        // 方式1：每一个单独处理
        // 处理1：销量
        if("xl".equals(searchVo.getSortBy())) {
            if("asc".equals(searchVo.getSortWay())) {
                // 升序
                queryBuilder.withSort(SortBuilders.fieldSort("sellerCount").order(SortOrder.ASC));
            } else {
                // 降序
                queryBuilder.withSort(SortBuilders.fieldSort("sellerCount").order(SortOrder.DESC));
            }
        }
        // 处理2：价格
        if("jg".equals(searchVo.getSortBy())) {
            if("asc".equals(searchVo.getSortWay())) {
                // 升序
                queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));
            } else {
                // 降序
                queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
            }
        }
        // 处理3：...
        */

        // 方式2：抽取
        Map<String,String> sortMap = new HashMap<>();
        sortMap.put("xl","sellerCount");
        sortMap.put("jg","price");
        sortMap.put("pl","commentCount");
        sortMap.put("sj","onSaleTime");
        // 获得排序字段
        String fieldName = sortMap.get(searchVo.getSortBy());
        // 排序方式
        SortOrder sortOrder = "asc".equals(searchVo.getSortWay()) ? SortOrder.ASC : SortOrder.DESC ;
        //
        queryBuilder.withSort(SortBuilders.fieldSort(fieldName).order(sortOrder));

        //3 查询
        Page<SearchSku> searchSkuPage = this.skuRepository.search(queryBuilder.build());

        //4 处理数据
        // 4.1 SearchSku --> ReturnSku
        List<ReturnSku> resultList = new ArrayList<>();
        for (SearchSku searchSku : searchSkuPage) {
            ReturnSku returnSku = new ReturnSku();
            returnSku.setId(searchSku.getId());
            returnSku.setGoodsName(searchSku.getSkuName());
            returnSku.setPrice(searchSku.getPrice());
            returnSku.setMidlogo(searchSku.getMidlogo());
            returnSku.setCommentCount(searchSku.getCommentCount());
            resultList.add(returnSku);
        }
        // 4.2 封装Map list、total
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("total", searchSkuPage.getTotalElements());
        resultMap.put("list", resultList);
        return resultMap;
    }
}
