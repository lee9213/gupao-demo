package com.lee9213.service.stub;

import com.lee9213.service.IHelloService;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-14 20:45
 */
public class HelloServiceStub implements IHelloService {

    private IHelloService helloService;

    public HelloServiceStub(IHelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public String hello(String message) {

        System.out.println("============server stub before=============");
        String hello = helloService.hello(message);
        System.out.println("============server stub after=============");
        return hello;
    }
}
