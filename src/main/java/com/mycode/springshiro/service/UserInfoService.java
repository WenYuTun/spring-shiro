package com.mycode.springshiro.service;

import com.mycode.springshiro.pojo.dto.UserInfoDto;
import com.mycode.springshiro.pojo.vo.request.UserLoginReq;

import java.util.List;

/**
 * @author wenyutun
 * @description: 用户接口
 * @date: 2019/8/19
 * @version: 1.0
 */
public interface UserInfoService {

    /**
     * 查询全部用户
     *
     * @return List<UserInfo>
     */
    List<UserInfoDto> getAllUsers();

    /**
     * 通过id查询用户信息
     *
     * @param id userID
     * @return userInfoDto
     */
    UserInfoDto selectUserById(Long id);

    /**
     * 登陆
     *
     * @param userLoginReq 用户登陆信息
     */
    void login(UserLoginReq userLoginReq);
}
