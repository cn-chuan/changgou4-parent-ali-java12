package com.czxy.changgou4;

import com.czxy.changgou4.domain.Item;
import com.czxy.changgou4.repository.ItemRepository;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.range.ParsedRange;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.bucket.range.RangeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
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
@SpringBootTest(classes = TestESApplication.class)
public class TestES_04_agg {

    @Resource
    private ItemRepository itemRepository;

    @Test
    public void testAggTerm() {
        // 根据品牌进行分桶操作
        //1 核心构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        //2 准备聚合条件
        TermsAggregationBuilder aggBuilder = AggregationBuilders.terms("term_brand").field("brand");
        aggBuilder.size(10);

        //3 添加聚合条件
        queryBuilder.addAggregation(aggBuilder);

        //4 执行查询
        AggregatedPage<Item> aggPage = (AggregatedPage<Item>) this.itemRepository.search(queryBuilder.build());

        //5 获得聚合结果
        ParsedStringTerms term_brand = (ParsedStringTerms) aggPage.getAggregation("term_brand");
        List<? extends Terms.Bucket> buckets = term_brand.getBuckets();
        //6 遍历所有的分桶
        for (Terms.Bucket bucket : buckets) {
            System.out.println("桶名：" + bucket.getKeyAsString());
            System.out.println("统计结果：" + bucket.getDocCount());
        }

    }


    @Test
    public void testSubAggTerm() {
        //1 核心构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        //2.1 term聚合条件
        TermsAggregationBuilder termsAgg = AggregationBuilders.terms("brands").field("brand").size(10);

        //2.2 range 子聚合
        RangeAggregationBuilder rangeAgg = AggregationBuilders.range("price_range").field("price");
        rangeAgg.addUnboundedTo(3300d).addRange(3300d, 4000d).addUnboundedFrom(4000d);

        //2.3 将子聚合整合到聚合中
        termsAgg.subAggregation(rangeAgg);

        //2.4 将聚合添加都条件
        queryBuilder.addAggregation(termsAgg);

        //3 查询
        AggregatedPage<Item> aggPage = (AggregatedPage<Item>) this.itemRepository.search(queryBuilder.build());

        //4.1 获得brands聚合
        ParsedStringTerms terms = (ParsedStringTerms) aggPage.getAggregation("brands");

        //4.2 遍历所有品牌桶
        List<? extends Terms.Bucket> brandBucketList = terms.getBuckets();
        for (Terms.Bucket brandBucket : brandBucketList) {
            System.out.println("品牌名称：" + brandBucket.getKeyAsString());

            //4.3 获得每一个品牌桶的子聚合 price_range
            ParsedRange priceAgg = brandBucket.getAggregations().get("price_range");

            //4.4 获得价格的所有桶
            List<? extends Range.Bucket> priceBucketList = priceAgg.getBuckets();

            //4.5 遍历
            for (Range.Bucket priceBucket : priceBucketList) {
                System.out.println("\tkey:" + priceBucket.getKeyAsString());
                System.out.println("\tfrom:" + priceBucket.getFromAsString());
                System.out.println("\tto:" + priceBucket.getToAsString());
                System.out.println("\tdoc:" + priceBucket.getDocCount());
                System.out.println();
            }
        }


    }






}
