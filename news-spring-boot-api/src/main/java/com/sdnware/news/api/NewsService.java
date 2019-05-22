package com.sdnware.news.api;


import java.util.List;
import java.util.Map;

/**
 * Created by sdnware on 18-8-16.
 */
public interface NewsService<T> {

    boolean saveNews(T t);

    boolean delNewsById(String id);

    boolean delAll();

    T findNewsById(String id);

    boolean bulkSaveNews(List<T> newsInfos);

    Map<String, Object> findTitleByField(Map<String, Object> fieldContentMap, List<String> highlightFields);

}
