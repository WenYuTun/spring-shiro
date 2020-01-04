package com.mycode.springshiro.dao;


import com.mycode.springshiro.pojo.entity.RolePermRel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RolePermRelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RolePermRel record);

    RolePermRel selectByPrimaryKey(Long id);

    List<RolePermRel> selectAll();

    int updateByPrimaryKey(RolePermRel record);
}