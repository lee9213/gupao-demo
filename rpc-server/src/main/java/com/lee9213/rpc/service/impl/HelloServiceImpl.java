package com.lee9213.rpc.service.impl;

import com.lee9213.service.IHelloService;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-07 20:43
 */
public class HelloServiceImpl implements IHelloService {

    @Override
    public String sayHello(String message) {
        return "hello, " + message;
    }
}
