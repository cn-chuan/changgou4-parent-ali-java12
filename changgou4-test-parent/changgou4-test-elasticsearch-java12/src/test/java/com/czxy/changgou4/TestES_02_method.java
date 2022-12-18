package com.czxy.changgou4;

import com.czxy.changgou4.domain.Item;
import com.czxy.changgou4.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestESApplication.class)
public class TestES_02_method {

    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Resource
    private ItemRepository itemRepository;

    @Test
    public void testFindAll() {
        // 查询所有
        Iterable<Item> list = itemRepository.findAll();
        for (Item item : list) {
            System.out.println(item);
        }
    }

    @Test
    public void testFindAllSort() {
        // 排序
        Iterable<Item> list = itemRepository.findAll(Sort.by("price").descending());
        for (Item item : list) {
            System.out.println(item);
        }
    }

    @Test
    public void testFindByTitle() {
        List<Item> list = itemRepository.findByTitle("手机");
        list.forEach(System.out::println);
    }

    @Test
    public void testFindByTitleAndBrand() {
        List<Item> list = itemRepository.findByTitleAndBrand("手机","小米");
        list.forEach(System.out::println);
    }

    @Test
    public void testFindByPriceLessThanEqual() {
        List<Item> list = itemRepository.findByPriceLessThanEqual(4000.0);
        list.forEach(System.out::println);
    }

    @Test
    public void testFindByPriceBetween() {
        List<Item> list = itemRepository.findByPriceBetween(3500d, 4000.0);
        list.forEach(System.out::println);
    }

    @Test
    public void testFindByBrandOrBrand() {
        List<Item> list = itemRepository.findByBrandOrBrand("小米", "锤子");
        list.forEach(System.out::println);
    }

    @Test
    public void testFindByBrandIn() {
        List<Item> list = itemRepository.findByBrandIn(Arrays.asList("小米", "锤子"));
        list.forEach(System.out::println);
    }

}
