package com.fangminx.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

import javax.annotation.Resource;

/**
 * Created by fangmin on 2017/7/17.
 * 声明Solrclient
 */
@Configuration
@EnableSolrRepositories(basePackages = {"com.fangminx"}, multicoreSupport = true)
public class SolrContext {

    static final String SOLR_HOST = "spring.data.solr.host";

    @Resource
    private Environment env;

    @Bean
    public SolrClient solrClient() {
        String solrHost = env.getRequiredProperty(SOLR_HOST);
        return new HttpSolrClient(solrHost);
    }

    @Bean
    public SolrTemplate solrTemplate(SolrClient solrClient) {
        return new SolrTemplate(solrClient);
    }
}