package com.sdnware.news.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * <p>Description: </p>
 *
 * @author Tanwei
 * @version 1.0
 * @createDate 2019/07/17 16:28
 * @see com.sdnware.news.datasource
 */
@Aspect
@Order(-1)
@Component
public class DynamicDataSourceAspect {

    private static final Logger LOG = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    @Before("@annotation(dataSource)")
    public void changeDataSource(DataSource dataSource) {
        String dbid = dataSource.name();
        if (DynamicDataSourceContextHolder.isContainsDataSource(dbid)) {
            LOG.info("Switch DataSource: " + dbid);
            DynamicDataSourceContextHolder.setDataSourceType(dbid);
        }
    }

    @After("@annotation(dataSource)")
    public void clearDataSource(DataSource dataSource) {
        DynamicDataSourceContextHolder.clearDataSourceType();
    }
}
