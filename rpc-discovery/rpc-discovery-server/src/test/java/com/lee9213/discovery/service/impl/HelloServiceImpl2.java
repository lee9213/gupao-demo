package com.lee9213.discovery.service.impl;

import com.lee9213.discovery.server.anno.RpcAnnotation;
import com.lee9213.service.IHelloService;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-13 0:41
 */
@RpcAnnotation(name = IHelloService.class, version = "2.0")
public class HelloServiceImpl2 implements IHelloService {


    @Override
    public String hello(String message) {
        return "[version=2.0]hello, i'm " + message;
    }
}
