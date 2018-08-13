package com.lee9213.discovery;

import com.lee9213.discovery.client.proxy.RpcProxy;
import com.lee9213.service.IHelloService;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-13 10:29
 */
public class ClientTest {


    public static void main(String[] args) {
        for(int i=0;i<20;i++){
            IHelloService helloService = RpcProxy.newInstance(IHelloService.class, i%2+1 + ".0");
            String message = helloService.hello("浪里小白龙");
            System.out.println(message);
        }

    }
}
