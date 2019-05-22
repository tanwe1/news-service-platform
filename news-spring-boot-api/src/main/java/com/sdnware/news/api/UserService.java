package com.sdnware.news.api;

import java.util.List;

/**
 * Created by towey on 2018/11/1.
 */
public interface UserService<T> {

    List<T> findUserByName(String username);

    T queryUserByName(String username, int level);

    T queryUserCustom(String username, int level);
}
