package com.lee9213.annotation.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lee9213.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-18 22:01
 */
@RestController
public class HelloController {

    @Reference
    private IUserService userService;

    @GetMapping("/")
    public void index(){
        System.out.println(userService.hello("annotation"));
    }
}
