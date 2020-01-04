package com.mycode.springshiro.service.impl;

import com.mycode.springshiro.dao.UserInfoMapper;
import com.mycode.springshiro.pojo.dto.UserInfoDto;
import com.mycode.springshiro.pojo.entity.UserInfo;
import com.mycode.springshiro.pojo.vo.request.UserLoginReq;
import com.mycode.springshiro.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author wenyutun
 * @description: 用户接口实现
 * @date: 2019/8/19
 * @version: 1.0
 */
@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    /**
     * 缓存前缀
     */
    private static final String CACHING_PREFIX = "user_info";

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfoDto> getAllUsers() {
        final List<UserInfo> userInfos = userInfoMapper.selectAll();
        return userInfos.stream().map(this::convertDtoInfoFromEntity).collect(Collectors.toList());
    }

    @Override
    public UserInfoDto selectUserById(Long id) {
        final String userId = CACHING_PREFIX + id;
        final ValueOperations ops = redisTemplate.opsForValue();
        UserInfo userInfo;
        if (redisTemplate.hasKey(userId)) {
            log.info("在redis中获取到key::: " + userId);
            userInfo = (UserInfo) ops.get(userId);
        } else {
            log.info("在内存中未找到key,执行数据库查询,key::: " + userId);
            userInfo = userInfoMapper.selectByPrimaryKey(id);
            ops.set(userId, userInfo, 1800, TimeUnit.SECONDS);
        }
        return convertDtoInfoFromEntity(userInfo);
    }

    @Override
    public void login(UserLoginReq userLoginReq) {

    }

    /**
     * 将用户实体模型数据复制到用户领域模型
     *
     * @param userInfo 用户实体模型
     * @return userInfoDto
     */
    private UserInfoDto convertDtoInfoFromEntity(UserInfo userInfo) {
        if (userInfo == null) {
            return null;
        }
        UserInfoDto userInfoDto = new UserInfoDto();
        BeanUtils.copyProperties(userInfo, userInfoDto);
        return userInfoDto;
    }
}
