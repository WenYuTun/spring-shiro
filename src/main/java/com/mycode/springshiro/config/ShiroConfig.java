package com.mycode.springshiro.config;

import com.google.common.collect.Maps;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author wenyutun
 * @description: shiro配置类
 * @date: 2019/8/21
 * @version: 1.0
 */
@Configuration
public class ShiroConfig {
    /**
     * 自定义realm
     *
     * @return CustomRealm
     */
    @Bean(name = "customRealm")
    public CustomRealm customRealm() {
        CustomRealm customRealm = new CustomRealm();
        //设置认证加密算法
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        customRealm.setCachingEnabled(true);
        return customRealm;
    }

    /**
     * 定义Security manager
     *
     * @param customRealm
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(CustomRealm customRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm);
        // 可不指定，Shiro会用默认Session manager
        securityManager.setSessionManager(sessionManager());
        //可不指定，Shiro会用默认CacheManager
        securityManager.setCacheManager(redisCacheManage());
        return securityManager;
    }

    /**
     * 自定义SessionManager,应用自定义SessionDao
     *
     * @return CustomerWebSessionManager
     */
    @Bean(name = "sessionManager")
    public CustomerWebSessionManager sessionManager() {
        CustomerWebSessionManager sessionManager = new CustomerWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDao());
        return sessionManager;
    }

    /**
     * 自定义RedisSessionDao用来管理Session在Redis中的CRUD
     *
     * @return redisSessionDao
     */
    @Bean(name = "redisSessionDao")
    public RedisSessionDao redisSessionDao() {
        return new RedisSessionDao();
    }

    /**
     * 自定义redis缓存管理器
     *
     * @return redisCacheManage
     */
    @Bean(name = "redisCacheManage")
    public RedisCacheManage redisCacheManage() {
        return new RedisCacheManage();
    }

    /**
     * shiro生命周期管理
     *
     * @return LifecycleBeanPostProcessor
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 定义shiro filter,shiro核心内容
     *
     * @param securityManager 权限管理器
     * @return ShiroFilterFactoryBean
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        HashMap<String, String> filterChainMap = Maps.newHashMap();
        filterChainMap.put("/user/login", "anon");
        filterChainMap.put("/user", "anon");
        filterChainMap.put("/user/logout", "anon");
        filterChainMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 定义加密匹配算法
     *
     * @return HashedCredentialsMatcher
     */
    @Bean(name = "credentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //散列算法，使用MD5
        matcher.setHashAlgorithmName("md5");
        //散列次数
        matcher.setHashIterations(1);
        //默认为Hex算法，false时用Base64
        matcher.setStoredCredentialsHexEncoded(true);
        return matcher;
    }

    /**
     * @param securityManager 权限管理器
     * @return AuthorizationAttributeSourceAdvisor
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    /**
     * @return DefaultAdvisorAutoProxyCreator
     */
    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        /*
            用于解决一个奇怪的bug,在引入spring aop的情况下
            在@Controller注解的类的方法中加入@RequiresRole等shiro注解。会导致该方法无法映射请求，返回404
            加入该项配置能解决这个bug
         */
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
    }

}
