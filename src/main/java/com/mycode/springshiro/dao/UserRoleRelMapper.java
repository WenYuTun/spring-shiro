package com.mycode.springshiro.dao;


import com.mycode.springshiro.pojo.entity.UserRoleRel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleRelMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserRoleRel record);

    UserRoleRel selectByPrimaryKey(Long id);

    List<UserRoleRel> selectAll();

    int updateByPrimaryKey(UserRoleRel record);
}