package com.mycode.springshiro.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author wenyutun
 * @description: 缓存管理
 * @date: 2019/8/10
 * @version: 1.0
 */
@Slf4j
@Component
public class RedisCache<K, V> implements Cache<K, V> {

    private static final String SHIRO_PREFIX = "shiro-cache:";

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private String getKey(K key){
        if (key instanceof String){
            return (SHIRO_PREFIX + key);
        }
        return key.toString();
    }

    @Override
    public V get(K k) throws CacheException {
        log.info("read from redis...");
        V v = (V) redisTemplate.opsForValue().get(getKey(k));
        return v;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        redisTemplate.opsForValue().set(getKey(k), v);
        redisTemplate.expire(getKey(k), 100, TimeUnit.SECONDS);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        V v = (V) redisTemplate.opsForValue().get(getKey(k));
        redisTemplate.delete((String) get(k));
        return v;
    }

    @Override
    public void clear() throws CacheException {
        //不要重写，如果只保存shiro数据无所谓
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}
