package com.lee9213.annotation;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-14 20:46
 */
@SpringBootApplication
@DubboComponentScan("com.lee9213.annotation.controller")
public class AnnotationClientApplication {

    public static void main(String[] args) {

        //运行方式
        SpringApplication.run(AnnotationClientApplication.class, args);
    }
}
