package com.gravity.demo.common.utils;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * esticsearch工具类
 *
 * @author qijunlin
 * @date 2019/10/28 11:31 上午
 */

public class ESUtils {
    public static void createIndex(String index) {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("user", "kimchy");
        jsonMap.put("postDate", new Date());
        jsonMap.put("message", "trying out Elasticsearch");
        IndexRequest indexRequest = new IndexRequest("posts")
                .id("1").source(jsonMap);
//        highLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }
}
