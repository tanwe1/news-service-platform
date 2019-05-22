package com.sdnware.news.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {


    /**
     * @Description: 用于组装Shiro-Filter
     * @param securityManager
     * @return org.apache.shiro.spring.web.ShiroFilterFactoryBean
     * @create 2019/5/16 18:10
     * @throws
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        //shiroFilterFactoryBean.setSuccessUrl("/index");
        //shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        Map<String, Filter> filters = new LinkedHashMap(); // 自定义
        //filters.put("kickout", shiroSessionControlFilter());
        shiroFilterFactoryBean.setFilters(filters);

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap();
        filterChainDefinitionMap.put("/login*", "anon");
        filterChainDefinitionMap.put("/40*", "anon");
        filterChainDefinitionMap.put("/common/**", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(RedisCacheManager redisCacheManager, ShiroSessionManager shiroSessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //ecurityManager.setCacheManager(redisCacheManager);
        //securityManager.setSessionManager(shiroSessionManager);
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }

    public ShiroRealm shiroRealm() {
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCredentialsMatcher(shiroCredentialsMatcher());
        return shiroRealm;
    }

    public ShiroCredentialsMatcher shiroCredentialsMatcher() {
        return new ShiroCredentialsMatcher();
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisManager redisManager) {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager);
        redisCacheManager.setKeyPrefix("SDNWARE_NEWS_CACHE:");
        return redisCacheManager;
    }

    @Bean
    public RedisManager redisManager(RedisConfig redisConfig) {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(redisConfig.redisHost);
        redisManager.setTimeout(redisConfig.redisTimeOut);
        return redisManager;
    }

    @Bean
    public ShiroSessionManager sessionManager(RedisSessionDAO redisSessionDAO) {
        SimpleCookie simpleCookie = new SimpleCookie("Token");
        simpleCookie.setPath("/");
        simpleCookie.setHttpOnly(true);

        ShiroSessionManager shiroSessionManager = new ShiroSessionManager();
        shiroSessionManager.setSessionDAO(redisSessionDAO);
        shiroSessionManager.setSessionIdCookieEnabled(false);
        shiroSessionManager.setSessionIdUrlRewritingEnabled(false);
        shiroSessionManager.setDeleteInvalidSessions(true);
        shiroSessionManager.setSessionIdCookie(simpleCookie);

        Collection<SessionListener> listeners = new ArrayList<>();
        ShiroSessionListener sessionListener = new ShiroSessionListener();
        listeners.add(sessionListener);
        shiroSessionManager.setSessionListeners(listeners);
        return shiroSessionManager;
    }


    @Bean
    public RedisSessionDAO redisSessionDAO(RedisManager redisManager) {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager);
        redisSessionDAO.setKeyPrefix("SDNWARE_NEWS_SESSION:");
        return redisSessionDAO;
    }

    /**
     * @Description: 会话控制
     * @param
     * @return ShiroSessionControlFilter
     * @create 2019/5/16 18:22
     * @throws
     */
    public ShiroSessionControlFilter shiroSessionControlFilter() {
        ShiroSessionControlFilter shiroSessionControlFilter = new ShiroSessionControlFilter();
        return shiroSessionControlFilter;
    }


    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * @Description: 生命周期通知
     * @param
     * @return org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator
     * @create 2019/5/16 18:15
     * @throws
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * @Description: 开启权限注解
     * @param securityManager
     * @return org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
     * @create 2019/5/16 18:12
     * @throws
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
