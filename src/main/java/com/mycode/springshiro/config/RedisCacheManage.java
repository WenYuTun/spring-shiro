package com.mycode.springshiro.config;


import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

import javax.annotation.Resource;

/**
 * @author wenyutun
 * @description: 自定义缓存管理器
 * @date: 2019/8/10
 * @version: 1.0
 */
public class RedisCacheManage implements CacheManager {

    @Resource
    private RedisCache redisCache;

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return redisCache;
    }
}
