package com.mycode.springshiro.config;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author wenyutun
 * @description: 自定义sessionDao
 * @date: 2019/8/10
 * @version: 1.0
 */
@Slf4j
public class RedisSessionDao extends AbstractSessionDAO {

    private static final String SESSION_PREFIX = "shiro-sessionId:";

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    protected Serializable doCreate(Session session) {
        log.info("Create Session");
        final Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        log.info("Read Session" + serializable.toString());
        final String key = getKey(serializable.toString());
        return (Session) redisTemplate.opsForValue().get(key);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        saveSession(session);
    }

    @Override
    public void delete(Session session) {
        if (session != null && session.getId() != null) {
            final String key = getKey(session.getId().toString());
            redisTemplate.delete(key);
        }
    }

    /**
     * 获取处于活跃状态的会话列表
     *
     * @return Collection<Session>
     */
    @Override
    public Collection<Session> getActiveSessions() {
        final Set<String> keys = redisTemplate.keys(SESSION_PREFIX + "*");
        Set<Session> sessions = Sets.newHashSet();
        if (CollectionUtils.isEmpty(keys)) {
            return sessions;
        }
        keys.forEach(key -> sessions.add((Session) redisTemplate.opsForValue().get(key)));
        return sessions;
    }

    /**
     * 获取key值
     *
     * @param key key值
     * @return final key
     */
    private String getKey(String key) {
        return SESSION_PREFIX + key;
    }

    /**
     * 保存session到redis服务器
     *
     * @param session 会话对象
     */
    private void saveSession(Session session) {
        if (session != null && session.getId() != null) {
            final String key = session.getId().toString();
            redisTemplate.opsForValue().set(getKey(key), session);
            redisTemplate.expire(getKey(key), 600, TimeUnit.SECONDS);
        }
    }
}
