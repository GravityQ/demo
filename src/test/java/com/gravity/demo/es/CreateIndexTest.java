package com.gravity.demo.es;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 创建索引
 *
 * @author qijunlin
 * @date 2019/10/25 6:22 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateIndexTest {
    @Autowired
    RestHighLevelClient highLevelClient;

    @Test
    public void create() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("user", "齐军林1");
        jsonMap.put("postDate", new Date());
        jsonMap.put("message", "创建document1");
        IndexRequest indexRequest = new IndexRequest("posts").id("1").source(jsonMap);
        highLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }

    @Test
    public void get() throws IOException {
        GetRequest getRequest = new GetRequest("posts", "1");
        GetResponse documentFields = highLevelClient.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(documentFields);
    }

    @Test
    public void exist() throws IOException {
        GetRequest getRequest = new GetRequest("posts", "1");
        getRequest.fetchSourceContext(FetchSourceContext.DO_NOT_FETCH_SOURCE).storedFields("_none_");
        System.out.println(highLevelClient.exists(getRequest, RequestOptions.DEFAULT));
    }

    @Test
    public void delete() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("posts", "1");
        System.out.println(highLevelClient.delete(deleteRequest, RequestOptions.DEFAULT));
    }

    @Test
    public void update() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("posts", "1");
        System.out.println(highLevelClient.update(updateRequest, RequestOptions.DEFAULT));
    }

    @Test
    public void query() {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("customer");
        searchRequest.types("_doc");

        // 条件=
        MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("city", "北京");
        TermQueryBuilder termQuery = QueryBuilders.termQuery("province", "福建");
        // 范围查询
        RangeQueryBuilder timeFilter = QueryBuilders.rangeQuery("log_time").gt(12345).lt(343750);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        QueryBuilder totalFilter = QueryBuilders.boolQuery()
                .filter(matchQuery)
                .filter(timeFilter)
                .mustNot(termQuery);

        int size = 200;
        int from = 0;
        long total = 0;

        do {
            try {
                sourceBuilder.query(totalFilter).from(from).size(size);
                sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
                searchRequest.source(sourceBuilder);

                SearchResponse response = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
                SearchHit[] hits = response.getHits().getHits();
                for (SearchHit hit : hits) {
                    System.out.println(hit.getSourceAsString());
                }

                total = response.getHits().getHits().length;

                System.out.println("测试:[" + total + "][" + from + "-" + (from + hits.length) + ")");

                from += hits.length;

                // from + size must be less than or equal to: [10000]
                if (from >= 10000) {
                    System.out.println("测试:超过10000条直接中断");
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (from < total);
    }
}
