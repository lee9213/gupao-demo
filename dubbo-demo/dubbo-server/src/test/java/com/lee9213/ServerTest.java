package com.lee9213;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.lee9213.service.IHelloService;
import com.lee9213.service.impl.HelloServiceImpl;
import org.junit.Test;

import java.io.IOException;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-15 22:49
 */
public class ServerTest {

    @Test
    public void provider() throws IOException {

        IHelloService helloService = new HelloServiceImpl();

        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("lee9213-dubbo-server");

        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://192.168.0.18:2181");

        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(20881);

        ServiceConfig<IHelloService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setInterface(IHelloService.class);
        serviceConfig.setRef(helloService);
        serviceConfig.setVersion("1.0");

        serviceConfig.export();

        System.in.read();
    }
}
