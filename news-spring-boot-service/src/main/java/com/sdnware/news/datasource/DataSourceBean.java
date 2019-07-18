package com.sdnware.news.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author Tanwei
 * @version 1.0
 * @createDate 2019/07/17 15:17
 * @see com.sdnware.news.datasource
 */
public class DataSourceBean implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private static final Logger LOG = LoggerFactory.getLogger(DataSourceBean.class);
    private static final String DATASOURCE_TYPE_DEFAULT = "com.zaxxer.hikari.HikariDataSource";

    private DataSource defaultDataSource;
    private Map<String, DataSource> otherDataSources = new HashMap<>();

    @Override
    public void setEnvironment(Environment environment) {
        LOG.info("Loading DataSource Environment ...");
        initDefaultDataSource(environment);
        initOtherDataSources(environment);
    }

    private void initDefaultDataSource(Environment environment) {
        Map<String, String> defaultDbProperties = new HashMap<>();
        defaultDbProperties.put("type", environment.getProperty("spring.datasource.type"));
        defaultDbProperties.put("url", environment.getProperty("spring.datasource.url"));
        defaultDbProperties.put("driver", environment.getProperty("spring.datasource.driver-class-name"));
        defaultDbProperties.put("username", environment.getProperty("spring.datasource.username"));
        defaultDbProperties.put("password", environment.getProperty("spring.datasource.password"));
        defaultDataSource = buildDataSource(defaultDbProperties);
    }

    private void initOtherDataSources(Environment environment) {
        String names = environment.getProperty("extends.datasource.names");
        String[] nameArr = names.split(",");
        for (String name : nameArr) {
            Map<String, String> otherDbProperties = new HashMap<>();
            otherDbProperties.put("type", environment.getProperty("extends.datasource." + name + ".type"));
            otherDbProperties.put("url", environment.getProperty("extends.datasource." + name + ".url"));
            otherDbProperties.put("driver", environment.getProperty("extends.datasource." + name + ".driver-class-name"));
            otherDbProperties.put("username", environment.getProperty("extends.datasource." + name + ".username"));
            otherDbProperties.put("password", environment.getProperty("extends.datasource." + name + ".password"));
            DataSource dataSource = buildDataSource(otherDbProperties);
            otherDataSources.put(name, dataSource);
        }
    }

    private DataSource buildDataSource(Map<String, String> dbProperties) {
        try {
            String type = dbProperties.get("type");
            if (type == null || type.isEmpty())
                type = DATASOURCE_TYPE_DEFAULT;
            Class<DataSource> typeClass = (Class<DataSource>)Class.forName(type);
            String url = dbProperties.get("url");
            String driver = dbProperties.get("driver");
            String username = dbProperties.get("username");
            String password = dbProperties.get("password");
            DataSource dataSource = DataSourceBuilder.create().url(url).driverClassName(driver).
                    username(username).password(password).type(typeClass).build();
            return dataSource;
        } catch (ClassNotFoundException e) {
            LOG.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        LOG.info("DataSource Bean Registering ...");
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("dataSource", this.defaultDataSource);
        DynamicDataSourceContextHolder.dataSourceIds.add("dataSource");
        targetDataSources.putAll(otherDataSources);
        for (String key : otherDataSources.keySet()) {
            DynamicDataSourceContextHolder.dataSourceIds.add(key);
        }

        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(DynamicDataSource.class);
        beanDefinition.setSynthetic(true);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);
        mpv.addPropertyValue("targetDataSources", targetDataSources);

        beanDefinitionRegistry.registerBeanDefinition("dataSource", beanDefinition);
    }
}
