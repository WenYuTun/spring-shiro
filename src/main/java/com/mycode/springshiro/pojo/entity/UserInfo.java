package com.mycode.springshiro.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wenyutun
 * @description: 用户信息表（user_info）实体类模型
 * @date: 2019/8/10
 * @version: 1.0
 */
@Data
public class UserInfo implements Serializable {


    public static final long serialVersionUID = 1L;
    /**
     * 用户ID(主键自增)
     */
    private Long id;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐值
     */
    private String salt;

}
