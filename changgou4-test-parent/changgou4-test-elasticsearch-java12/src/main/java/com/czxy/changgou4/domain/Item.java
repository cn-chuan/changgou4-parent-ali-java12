package com.czxy.changgou4.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "item", type = "docs", shards = 1, replicas = 0)
public class Item {
    @Id
    private Long id;
    @Field(type = FieldType.Text, index = true, store = false, analyzer = "ik_max_word")
    private String title; 			//标题
    @Field(type = FieldType.Keyword)
    private String category;		//分类
    @Field(type = FieldType.Keyword)
    private String brand; 			//品牌
    @Field(type = FieldType.Double)
    private Double price; 			//价格
    @Field(type = FieldType.Keyword, index = false)
    private String images; 		    //图片地址

}
