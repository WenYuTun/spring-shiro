package com.mycode.springshiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author wenyutun
 * @description: 工程入口
 * @date: 2019/8/19
 * @version: 1.0
 */
@SpringBootApplication
@EnableCaching
public class SpringShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringShiroApplication.class, args);
    }

}
