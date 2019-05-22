package com.sdnware.news.dao.es;

import com.sdnware.news.pojo.es.NewsInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by sdnware on 18-8-16.
 */
public interface NewsRepository extends ElasticsearchRepository<NewsInfo, String> {
}
