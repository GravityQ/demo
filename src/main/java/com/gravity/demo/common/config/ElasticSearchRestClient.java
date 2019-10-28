package com.gravity.demo.common.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * es配置文件
 * https://www.cnblogs.com/ginb/p/8716485.html
 * @author qijunlin
 * @date 2019/10/25 7:05 下午
 */
@Configuration
public class ElasticSearchRestClient {
    @Value("${elasticsearch.ip}")
    private String ipPort;

    @Bean
    public RestClientBuilder restClientBuilder() {
        String[] address = ipPort.split(":");
        String ip = address[0];
        int port = Integer.parseInt(address[1]);
        HttpHost httpHost = new HttpHost(ip, port, "http");
        return RestClient.builder(httpHost);
    }

    @Bean
    public RestHighLevelClient restHighLevelClient(@Autowired RestClientBuilder restClientBuilder) {
        return new RestHighLevelClient(restClientBuilder());
    }
}
