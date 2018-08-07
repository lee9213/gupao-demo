package com.lee9213;

import com.lee9213.rpc.client.RpcClientProxy;
import com.lee9213.service.IHelloService;

/**
 * Hello world!
 */
public class RpcClientApp {
    public static void main(String[] args) {

        IHelloService o = RpcClientProxy.newProxy(IHelloService.class, "localhost", 9999);
        String lee9213 = o.sayHello("what's your name?");
        System.out.println(lee9213);
    }
}
