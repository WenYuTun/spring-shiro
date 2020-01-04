package com.mycode.springshiro.pojo.entity;

import lombok.Data;

/**
 * @author wenyutun
 * @description: 角色与权限关系表（role_perm_rel）实体类模型
 * @date: 2019/8/10
 * @version: 1.0
 */
@Data
public class RolePermRel {

    /**
     * ID
     */
    private Long id;

    /**
     * 权限ID
     */
    private Long permId;

    /**
     * 角色ID
     */
    private Long roleId;
}
