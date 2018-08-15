package com.lee9213;

import com.lee9213.service.IHelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-14 23:26
 */
public class ClientApplication {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/spring/spring.xml");
        IHelloService helloService = (IHelloService) applicationContext.getBean("helloService");
        String message = helloService.hello("浪里小白龙");
        System.out.println(message);
    }
}
