package com.mycode.springshiro.config;

import com.mycode.springshiro.dao.RoleInfoMapper;
import com.mycode.springshiro.dao.UserInfoMapper;
import com.mycode.springshiro.pojo.entity.RoleInfo;
import com.mycode.springshiro.pojo.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wenyutun
 * @description: 自定义realm
 * @date: 2019/8/21
 * @version: 1.0
 */
@Slf4j
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private RoleInfoMapper roleInfoMapper;

    /**
     * 授权
     *
     * @param principalCollection 入参
     * @return AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("CustomRealm doGetAuthorizationInfo ,shiro用户授权read from db....");
        final UserInfo userInfo = (UserInfo) super.getAvailablePrincipal(principalCollection);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        final List<Long> roleIds = roleInfoMapper.findUserRole(userInfo.getId());
        final List<RoleInfo> roleInfos =roleInfoMapper.findByIdIn(roleIds);
        authorizationInfo.setRoles(roleInfos.stream().map(RoleInfo::getRoleCode).collect(Collectors.toSet()));
        return authorizationInfo;
    }

    /**
     * 认证
     *
     * @param authenticationToken 认证信息
     * @return AuthenticationInfo
     * @throws AuthenticationException 认证失败异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        log.info("CustomRealm doGetAuthenticationInfo,shiro认证用户");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        final UserInfo userInfo = userInfoMapper.findUserInfoByUserName(username);
        final SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userInfo,
                userInfo.getPassword(), getName());
        simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(userInfo.getSalt()));
        return simpleAuthenticationInfo;
    }
}
