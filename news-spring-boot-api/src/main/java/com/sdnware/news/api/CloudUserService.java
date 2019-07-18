package com.sdnware.news.api;

/**
 * Created by towey on 2018/11/1.
 */
public interface CloudUserService<T> {

    T queryUserById(Integer id);
}
