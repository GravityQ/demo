package com.gravity.demo.entity.es;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * es测试对象
 * https://blog.csdn.net/chen_2890/article/details/83895646
 *
 * @author qijunlin
 * @date 2019/10/25 6:13 下午
 */
@Data
/**
 * indexName：对应索引库名称
 * type：对应在索引库中的类型
 * shards：分片数量，默认5
 * replicas：副本数量，默认1
 */
//@Document(indexName = "item",type = "docs",shards = 1,replicas = 0)
public class Item {
    /**
     * @Description: @Id注解必须是springframework包下的
     * org.springframework.data.annotation.Id
     * @Author: https://blog.csdn.net/chen_2890
     */
    @Id
    private Long id;
    /**
     * 标题
     * analyzer:分词器
     */
//    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;
    /**
     * 分类
     * Keyword:不会分词
     */
//    @Field(type = FieldType.Keyword)
    private String category;
    /**
     * 品牌
     */
//    @Field(type = FieldType.Keyword)
    private String brand;
    /**
     * 价格
     */
//    @Field(type = FieldType.Double)
    private Double price;
    /**
     * 图片地址
     * index：是否索引，布尔类型，默认是true
     */
//    @Field(index = false, type = FieldType.Keyword)
    private String images;

}
