package com.mycode.springshiro.config;

import com.mycode.springshiro.pojo.vo.ResponseJsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wenyutun
 * @description: 统一异常处理类
 * @date: 2019/8/10
 * @version: 1.0
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    private ResponseJsonData exceptionHandler(Exception e){
        log.error("Exception>>>>>>>>",e);
        //返回自定义异常信息
        return ResponseJsonData.errorException(e.getMessage());
    }

}
