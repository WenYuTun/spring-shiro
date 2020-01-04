package com.mycode.springshiro.pojo.dto;

import lombok.Data;

/**
 * @author wenyutun
 * @description: 用户领域模型
 * @date: 2019/8/10
 * @version: 1.0
 */
@Data
public class UserInfoDto {

    /**
     * 用户ID(主键自增)
     */
    private Long id;

    /**
     * 用户姓名
     */
    private String userName;


}
