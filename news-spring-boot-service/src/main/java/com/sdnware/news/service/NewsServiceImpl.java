package com.sdnware.news.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.sdnware.news.api.NewsService;
import com.sdnware.news.dao.es.NewsRepository;
import com.sdnware.news.pojo.es.NewsInfo;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.*;

/**
 * Created by sdnware on 18-8-16.
 */
@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class NewsServiceImpl implements NewsService<NewsInfo> {

    private static final Logger LOG = LoggerFactory.getLogger(NewsServiceImpl.class);

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public boolean saveNews(NewsInfo newsInfo) {
        NewsInfo save = newsRepository.save(newsInfo);
        if(save == null)
            return false;
        return true;
    }

    @Override
    public boolean delNewsById(String id) {
        boolean res = Boolean.FALSE;
        try {
            newsRepository.deleteById(id);
            res = Boolean.TRUE;
        }catch (Exception e) {
            LOG.error("delete news data is error.");
        }
        return res;
    }

    @Override
    public boolean delAll() {
        boolean res = Boolean.FALSE;
        try {
            newsRepository.deleteAll();
            res = Boolean.TRUE;
        }catch (Exception e) {
            LOG.error("delete news data is error.");
        }
        return res;
    }

    @Override
    public NewsInfo findNewsById(String id) {
        Optional<NewsInfo> newsOptional = newsRepository.findById(id);
        return newsOptional.get();
    }

    @Override
    public boolean bulkSaveNews(List<NewsInfo> newsInfos) {
        List<IndexQuery> queries = new ArrayList<>();
        for (NewsInfo newsInfo : newsInfos) {
            IndexQuery indexQuery = new IndexQuery();
            indexQuery.setId(newsInfo.getId());
            indexQuery.setObject(newsInfo);
            indexQuery.setIndexName(NEWS_INDEX_NAME);
            indexQuery.setType(NEWS_TYPE_NAME);
            queries.add(indexQuery);
        }
        if (queries.size() > 0) {
            elasticsearchTemplate.bulkIndex(queries);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Map<String, Object> findTitleByField(Map<String,Object> fieldContentMap, List<String> highlightFields) {
        Map<String, Object> resultPageContent = new HashMap<>();
        AggregatedPage<NewsInfo> resultPage = null;
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        if (!fieldContentMap.isEmpty()) {
            for (String key : fieldContentMap.keySet()) {
                boolQueryBuilder.must(QueryBuilders.matchQuery(key, fieldContentMap.get(key)));
            }

            HighlightBuilder.Field[] highlightFieldArr = null;
            if (highlightFields != null) {
                highlightFieldArr = new HighlightBuilder.Field[highlightFields.size()];
                for (int i = 0; i < highlightFields.size(); i++) {
                    highlightFieldArr[i] = new HighlightBuilder.Field(highlightFields.get(i)).preTags("<em style='color:red'>").postTags("</em>").fragmentSize(250);
                }
            }
            SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).withHighlightFields(highlightFieldArr).build();

            if (highlightFields != null && !highlightFields.isEmpty()) {
                resultPage = elasticsearchTemplate.queryForPage(searchQuery, NewsInfo.class, new SearchResultMapper() {
                    @Override
                    public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                        List<NewsInfo> chunk = new ArrayList<>();
                        for (SearchHit searchHit : response.getHits()) {
                            if (response.getHits().getHits().length <= 0)
                                return null;
                            NewsInfo newsInfo = new NewsInfo();
                            newsInfo.setId(searchHit.getId());
                            for (String field : highlightFields) {
                                HighlightField highlightField = searchHit.getHighlightFields().get(field);
                                if (highlightField != null) {
                                    if (field.equalsIgnoreCase("title"))
                                        newsInfo.setTitle(highlightField.fragments()[0].string());
                                    if (field.equalsIgnoreCase("content"))
                                        newsInfo.setContent(highlightField.fragments()[0].string());
                                }
                            }

                            Map<String, Object> source = searchHit.getSource();
                            newsInfo.setSubTitle(source.get("subTitle").toString());
                            newsInfo.setPublicTime(source.get("publicTime").toString());
                            newsInfo.setAuthor(source.get("author").toString());
                            //newsInfo.setContent(source.get("content").toString());
                            newsInfo.setState(Integer.valueOf(source.get("state").toString()));
                            newsInfo.setReadCount(Long.valueOf(source.get("readCount").toString()));
                            chunk.add(newsInfo);
                        }
                        return new AggregatedPageImpl<>((List<T>) chunk);
                    }
                });
            } else {
                resultPage = elasticsearchTemplate.queryForPage(searchQuery, NewsInfo.class);
            }
        }
        resultPageContent.put("totalElements", resultPage.getTotalElements());
        resultPageContent.put("totalPages", resultPage.getTotalPages());
        resultPageContent.put("newsContent", resultPage.getContent());

        return resultPageContent;
    }

    private static final String NEWS_INDEX_NAME = "idx_news";
    private static final String NEWS_TYPE_NAME = "tb_news";
}
