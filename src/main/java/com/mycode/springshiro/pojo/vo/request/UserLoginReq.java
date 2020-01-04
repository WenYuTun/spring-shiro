package com.mycode.springshiro.pojo.vo.request;

import lombok.Data;

/**
 * @author wenyutun
 * @description: 用户登陆请求参数模型
 * @date: 2019/8/10
 * @version: 1.0
 */
@Data
public class UserLoginReq {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;
}
