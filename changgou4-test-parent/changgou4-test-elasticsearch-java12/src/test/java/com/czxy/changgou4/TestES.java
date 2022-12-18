package com.czxy.changgou4;

import com.czxy.changgou4.domain.Item;
import com.czxy.changgou4.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestESApplication.class)
public class TestES {

    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Resource
    private ItemRepository itemRepository;

    @Test
    public void testCreateIndex() {
        //创建索引
        elasticsearchRestTemplate.createIndex(Item.class);
        System.out.println("创建索引成功");
        // 创建映射
        elasticsearchRestTemplate.putMapping(Item.class);
    }

    @Test
    public void testDeleteIndex() {
        elasticsearchRestTemplate.deleteIndex(Item.class);
    }

    @Test
    public void testSave() {
        Item item = new Item(1L, "小米手机7", "手机",
                "小米", 3499.00, "http://image.baidu.com/13123.jpg");
        // 添加一个数据
        itemRepository.save(item);
    }

    @Test
    public void testSaveAll() {
        List<Item> list = new ArrayList<>();
        list.add(new Item(2L, "坚果手机R1", "手机", "锤子", 3699.00, "http://image.baidu.com/13123.jpg"));
        list.add(new Item(3L, "华为META10", "手机", "华为", 4499.00, "http://image.baidu.com/13123.jpg"));
        list.add(new Item(4L, "小米Mix2S", "手机", "小米", 4299.00, "http://image.baidu.com/13123.jpg"));
        list.add(new Item(5L, "荣耀V10", "手机", "华为", 2799.00, "http://image.baidu.com/13123.jpg"));

        //保存所有
        itemRepository.saveAll(list);
    }

    @Test
    public void testDelete() {
        itemRepository.deleteById(1L);
    }
}
