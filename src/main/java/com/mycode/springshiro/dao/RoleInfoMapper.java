package com.mycode.springshiro.dao;


import com.mycode.springshiro.pojo.entity.RoleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wenyutun
 * @description: 角色DAO
 * @date: 2019/8/19
 * @version: 1.0
 */
@Mapper
@Repository
public interface RoleInfoMapper {

    /**
     * 删除角色
     *
     * @param id ID
     * @return 成功条数
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 添加
     *
     * @param record 角色信息
     * @return 成功条数
     */
    int insert(RoleInfo record);

    /**
     * 通过主键查询
     *
     * @param id 角色ID
     * @return roleInfo
     */
    RoleInfo selectByPrimaryKey(Long id);

    /**
     * 获取全部
     *
     * @return 角色列表
     */
    List<RoleInfo> selectAll();

    /**
     * 更新
     *
     * @param record 角色信息
     * @return 成功条数
     */
    int updateByPrimaryKey(RoleInfo record);

    /**
     * 获取用户角色信息
     *
     * @param id 用户ID
     * @return 角色id列表
     */
    List<Long> findUserRole(Long id);


    /**
     * 通过角色ID列表查询
     *
     * @param roleIds 角色Ids
     * @return 角色列表
     */
    List<RoleInfo> findByIdIn(List<Long> roleIds);
}