package com.mycode.springshiro.dao;


import com.mycode.springshiro.pojo.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wenyutun
 * @description: 用户DAO
 * @date: 2019/8/19
 * @version: 1.0
 */
@Mapper
@Repository
public interface UserInfoMapper {

    /**
     * 通过主键删除
     *
     * @param id 用户ID
     * @return 成功条数
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 添加
     *
     * @param record 用户实体
     * @return 成功条数
     */
    int insert(UserInfo record);

    /**
     * 通过主键获取用户信息
     *
     * @param id ID
     * @return userInfo
     */
    UserInfo selectByPrimaryKey(Long id);

    /**
     * 获取全部
     *
     * @return 用户列表
     */
    List<UserInfo> selectAll();

    /**
     * 更新
     *
     * @param record 用户实体
     * @return 成功条数
     */
    int updateByPrimaryKey(UserInfo record);


    /**
     * 通过姓名获取用户信息
     *
     * @param username 姓名
     * @return userInfo
     */
    UserInfo findUserInfoByUserName(String username);
}