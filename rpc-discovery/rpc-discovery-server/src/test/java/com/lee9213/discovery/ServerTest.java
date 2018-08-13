package com.lee9213.discovery;

import com.lee9213.discovery.server.DiscoveryServer;
import com.lee9213.discovery.service.impl.HelloServiceImpl;
import com.lee9213.discovery.service.impl.HelloServiceImpl2;
import com.lee9213.service.IHelloService;
import org.junit.Test;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-13 0:35
 */
public class ServerTest {


    @Test
    public void test1() {

        IHelloService helloService = new HelloServiceImpl();
        DiscoveryServer discovery = new DiscoveryServer();
        discovery.register(helloService, "127.0.0.1:8080");
    }

    @Test
    public void test2() {
        IHelloService helloService = new HelloServiceImpl2();
        DiscoveryServer discovery = new DiscoveryServer();
        discovery.register(helloService, "127.0.0.1:8081");
    }
}
