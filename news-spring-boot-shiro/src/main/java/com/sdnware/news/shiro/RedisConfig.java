package com.sdnware.news.shiro;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p>Description: </p>
 *
 * @author Tanwei
 * @version 1.0
 * @createDate 2019/05/17 10:42
 * @see com.sdnware.news.shiro
 */
@Component
public class RedisConfig {

    @Value("${redis.host}")
    public String redisHost;

    @Value("${redis.port}")
    public int redisPort;

    @Value("${redis.timeout}")
    public int redisTimeOut;

}
