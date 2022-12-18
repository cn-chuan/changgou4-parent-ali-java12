package com.czxy.changgou4;

import com.czxy.changgou4.domain.Item;
import com.czxy.changgou4.repository.ItemRepository;
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
@SpringBootTest(classes = TestESApplication.class)
public class TestES_03_search {

    @Resource
    private ItemRepository itemRepository;

    @Test
    public void testFindByTitle() {
        // 匹配查询，title=手机
        //1 核心构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //2 设置query查询条件
        queryBuilder.withQuery(QueryBuilders.matchQuery("title","手机"));
        //3 查询
        Page<Item> itemPage = itemRepository.search(queryBuilder.build());
        //4 打印
        System.out.println("总条数" + itemPage.getTotalElements());
        List<Item> list = itemPage.getContent();
        for (Item item : list) {
            System.out.println(item);
        }

    }

    @Test
    public void testFindByTitleAndBrand() {
        //1 核心构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //2 构建 bool 查询条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchQuery("title","手机"));
        boolQueryBuilder.must(QueryBuilders.matchQuery("brand","小米"));

        //3 将bool条件构建器
        queryBuilder.withQuery(boolQueryBuilder);

        //4 查询
        Page<Item> itemPage = itemRepository.search(queryBuilder.build());

        //5 处理数据
        System.out.println(itemPage.getTotalElements());
        itemPage.forEach(System.out::println);

    }


    //练习2：查询标题中含“手机”，且品牌不是“小米”的商品列表信息
    @Test
    public void testFindByTitleAndBrandNot() {
        //1 核心构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //2 构建 bool 查询条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchQuery("title","手机"));
        boolQueryBuilder.mustNot(QueryBuilders.matchQuery("brand","小米"));

        //3 将bool条件构建器
        queryBuilder.withQuery(boolQueryBuilder);

        //4 查询
        Page<Item> itemPage = itemRepository.search(queryBuilder.build());

        //5 处理数据
        System.out.println(itemPage.getTotalElements());
        itemPage.forEach(System.out::println);

    }

    @Test
    public void testTerm() {
        // 精确查询 brand=小米
        //1 核心构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //2 条件
        queryBuilder.withQuery(QueryBuilders.termQuery("brand","小米"));
        //3 查询
        Page<Item> itemPage = this.itemRepository.search(queryBuilder.build());
        //4 处理结果
        System.out.println(itemPage.getTotalElements());
        itemPage.forEach(System.out::println);
    }

    @Test
    public void testPage() {
        // 分页查询
        //1 核心构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //2 分页参数：page表示页数，但从0开始的
        queryBuilder.withPageable(PageRequest.of(1,2));
        //3 查询
        Page<Item> itemPage = this.itemRepository.search(queryBuilder.build());
        //4 处理结果
        System.out.println("总记录数：" + itemPage.getTotalElements());
        System.out.println("总分页数：" + itemPage.getTotalPages());
        itemPage.forEach(System.out::println);

    }

    @Test
    public void testSort() {
        // 排序查询  price
        //1 核心构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //2 排序查询
        queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
        //3 查询
        Page<Item> itemPage = this.itemRepository.search(queryBuilder.build());
        //4 处理结果
        System.out.println("总记录数：" + itemPage.getTotalElements());
        System.out.println("总分页数：" + itemPage.getTotalPages());
        itemPage.forEach(System.out::println);

    }

    @Test
    public void testRange() {
        // 范围查询  price

        //1 核心构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //2 范围查询
        queryBuilder.withQuery(QueryBuilders.rangeQuery("price").gte(3000d).lte(4300d));
        //3 查询
        Page<Item> itemPage = this.itemRepository.search(queryBuilder.build());
        //4 处理结果
        System.out.println("总记录数：" + itemPage.getTotalElements());
        System.out.println("总分页数：" + itemPage.getTotalPages());
        itemPage.forEach(System.out::println);

    }










}
