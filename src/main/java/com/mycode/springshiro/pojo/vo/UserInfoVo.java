package com.mycode.springshiro.pojo.vo;

import lombok.Data;

/**
 * @author wenyutun
 * @description: 用户视图模型
 * @date: 2019/8/10
 * @version: 1.0
 */
@Data
public class UserInfoVo {

    /**
     * 用户ID(主键自增)
     */
    private Long id;

    /**
     * 用户姓名
     */
    private String userName;

}
