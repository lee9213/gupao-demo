package com.lee9213;

import com.alibaba.dubbo.rpc.RpcContext;
import com.lee9213.service.IHelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-14 23:26
 */
public class ClientApplication {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/spring/spring.xml");
        IHelloService helloService = (IHelloService) applicationContext.getBean("helloService");

//
//        EchoService echoService = (EchoService) helloService;
//        Object ok = echoService.$echo("ok");

        String message = helloService.hello("浪里小白龙");
        System.out.println(message);
        Future<Object> future = RpcContext.getContext().getFuture();
        Object o = future.get();
        System.out.println(o);


//        IUserService userService = (IUserService) applicationContext.getBean("userService");
//        System.out.println(userService.hello("UserService"));
//        System.in.read();

    }
}
