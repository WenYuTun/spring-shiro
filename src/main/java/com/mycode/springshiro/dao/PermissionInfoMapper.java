package com.mycode.springshiro.dao;


import com.mycode.springshiro.pojo.entity.PermissionInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(PermissionInfo record);

    PermissionInfo selectByPrimaryKey(Long id);

    List<PermissionInfo> selectAll();

    int updateByPrimaryKey(PermissionInfo record);
}