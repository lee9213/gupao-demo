package com.lee9213.service.impl;

import com.lee9213.service.IHelloService;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-14 20:45
 */
public class HelloServiceImpl2 implements IHelloService {


    @Override
    public String hello(String message) {
        return "[2]hello, i'm " + message;
    }
}
