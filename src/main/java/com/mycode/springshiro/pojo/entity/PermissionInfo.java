package com.mycode.springshiro.pojo.entity;

import lombok.Data;


/**
 * @author wenyutun
 * @description: 权限表（permission_info）实体类模型
 * @date: 2019/8/10
 * @version: 1.0
 */
@Data
public class PermissionInfo {

    /**
     * 权限ID
     */
    private Long id;

    /**
     * 权限编码
     */
    private String permCode;

    /**
     * 权限名称
     */
    private String permName;
}
