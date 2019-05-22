package com.sdnware.news.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * <p>Description: </p>
 *
 * @author Tanwei
 * @version 1.0
 * @createDate 2019/05/20 14:50
 * @see com.sdnware.news.common
 */
@Component
public class SpringBeanFactory implements ApplicationContextAware {


    private static ApplicationContext context = null;

    public static <T> T getBean(Class<T> type) {
        return context.getBean(type);
    }

    public static <T> T getBean(String name, Class<T> type) {
        return context.getBean(name, type);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringBeanFactory.context == null) {
            SpringBeanFactory.context = applicationContext;
        }
    }
}
