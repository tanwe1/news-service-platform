package com.sdnware.news.api;

import java.util.List;

public interface ProbeService<T> {

    long count(String collectionName);

    List<T> findAll(String collectionName);
}
