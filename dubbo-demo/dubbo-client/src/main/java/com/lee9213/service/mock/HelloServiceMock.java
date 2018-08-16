package com.lee9213.service.mock;

import com.lee9213.service.IHelloService;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-15 23:34
 */
public class HelloServiceMock implements IHelloService {

    @Override
    public String hello(String message) {
        return "=====================this is mock=====================";
    }
}
