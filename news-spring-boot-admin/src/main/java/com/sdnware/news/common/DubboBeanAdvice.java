package com.sdnware.news.common;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sdnware.news.api.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * <p>Description: </p>
 *
 * @author Tanwei
 * @version 1.0
 * @createDate 2019/05/20 14:46
 * @see com.sdnware.news.common
 */
@Component
public class DubboBeanAdvice {

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "${dubbo.reference.url}")
    private UserService userService;

    @Bean(name = "userService")
    public UserService getUserService() {
        return userService;
    }
}
