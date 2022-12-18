package com.czxy.changgou4.repository;

import com.czxy.changgou4.domain.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
public interface ItemRepository extends ElasticsearchRepository<Item, Long> {
    // 指定title查询，等效match查询
    public List<Item> findByTitle(String title);

    // 指定 title和brand ，等效 bool + must + match 查询
    public List<Item> findByTitleAndBrand(String title, String brand);

    // 价格小于等于 ？ （ 4000 ）
    public List<Item> findByPriceLessThanEqual(Double price);

    // 价格大于等于 ？ （ 3500 ）
    public List<Item> findByPriceGreaterThanEqual(Double price);

    // 区间
    public List<Item> findByPriceBetween(Double start, Double end);

    // 品牌既是小米又是锤子
    public List<Item> findByBrandOrBrand(String b1, String b2);
    public List<Item> findByBrandIn(List<String> brands);


}
