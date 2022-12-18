package com.czxy.changgou4;

import com.czxy.changgou4.repository.SkuRepository;
import com.czxy.changgou4.vo.BaseResult;
import com.czxy.changgou4.vo.SearchSku;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CGSearchServiceApplication.class)
public class TestSkuRepository {

    @Resource
    private SkuRepository skuRepository;

    @Test
    public void testSearch() {
        //1 多条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 1.1 分类查询
//        boolQueryBuilder.must(QueryBuilders.termQuery("catId", 76));
        // 1.2 品牌查询
//        boolQueryBuilder.must(QueryBuilders.termQuery("brandId", "8557"));
        // 1.3 指定规格查询
//        boolQueryBuilder.must(QueryBuilders.termQuery("specs.机身颜色.keyword", "粉色"));
        // 1.4 价格区间查询（单位：分）
//        boolQueryBuilder.must(QueryBuilders.rangeQuery("price").gte("90000"));
//        boolQueryBuilder.must(QueryBuilders.rangeQuery("price").lte("120000"));
        // 1.5 关键字（模糊、匹配）,可以使用skuName，也可以使用all
//        boolQueryBuilder.must(QueryBuilders.matchQuery("skuName", "华为"));

        //2 核心类，并使用多条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 2.1 查询条件
        queryBuilder.withQuery(boolQueryBuilder);
        // 2.2 分页
        queryBuilder.withPageable(PageRequest.of(0, 3));
        // 2.3 排序 (价格)
        queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));


        //3 搜索
        Page<SearchSku> searchSkuPage = skuRepository.search(queryBuilder.build());

        //4 处理结果
        System.out.println("总条数：" + searchSkuPage.getTotalElements());
        System.out.println("分页数：" + searchSkuPage.getTotalPages());
        for (SearchSku searchSku : searchSkuPage.getContent()) {
            System.out.println(searchSku);
        }
    }
}
