package com.mycode.springshiro.pojo.entity;

import lombok.Data;


/**
 * @author wenyutun
 * @description: 用户与角色关系表（user_rol_rel）实体类模型
 * @date: 2019/8/10
 * @version: 1.0
 */
@Data
public class UserRoleRel {

    /**
     * ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;
}
