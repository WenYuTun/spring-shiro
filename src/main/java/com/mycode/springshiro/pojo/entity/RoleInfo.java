package com.mycode.springshiro.pojo.entity;

import lombok.Data;



/**
 * @author wenyutun
 * @description: 角色信息表（role_info）实体类模型
 * @date: 2019/8/10
 * @version: 1.0
 */
@Data
public class RoleInfo {

    /**
     * 角色ID(主键自增)
     */
    private Long id;

    /**
     * 角色编码（唯一）
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;
}
