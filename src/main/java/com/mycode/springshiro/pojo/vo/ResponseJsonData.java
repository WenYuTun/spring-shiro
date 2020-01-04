package com.mycode.springshiro.pojo.vo;

import lombok.Data;

/**
 * @author wenyutun
 * @description: 自定义响应数据结构
 * 200：表示成功
 * 500：表示错误，错误信息哎msg中
 * 501：bean验证错误，以map形式返回
 * 502：拦截器拦截到用户token出错
 * 555：异常抛出信息
 * @date: 2019/8/10
 * @version: 1.0
 */
@Data
public class ResponseJsonData {

    /**
     * 响应业务状态
      */
    private Integer status;

    /**
     * 响应消息
      */
    private String msg;

    /**
     * 响应数据
     */
    private Object data;

    public static ResponseJsonData build(Integer status, String msg, Object data) {
        return new ResponseJsonData(status, msg, data);
    }

    /**
     * 成功调用函数
     * @param data 数据
     * @return ResponseJsonData
     */
    public static ResponseJsonData ok(Object data) {
        return new ResponseJsonData(data);
    }

    public static ResponseJsonData ok(String msg) {
        return new ResponseJsonData(msg);
    }

    public static ResponseJsonData ok(String msg, Object data) {
        return new ResponseJsonData(msg, data);
    }

    /**
     * 失败调用函数
     */
    public static ResponseJsonData errorMsg(String msg) {
        return new ResponseJsonData(500, msg, null);
    }

    public static ResponseJsonData errorMap(Object data) {
        return new ResponseJsonData(501, "error", data);
    }

    public static ResponseJsonData errorTokenMsg(String msg) {
        return new ResponseJsonData(502, msg, null);
    }

    /**
     * 异常调用函数
     */
    public static ResponseJsonData errorException(String msg) {
        return new ResponseJsonData(555, msg, null);
    }


    /**
     * 空参构造
     */
    public ResponseJsonData() {

    }

    private ResponseJsonData(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private ResponseJsonData(Object data) {
        this.status = 200;
        this.msg = "ok";
        this.data = data;
    }

    private ResponseJsonData(String msg, Object data) {
        this.status = 200;
        this.msg = msg;
        this.data = data;
    }

    private ResponseJsonData(String msg) {
        this.status = 200;
        this.msg = msg;
        this.data = null;
    }

}
