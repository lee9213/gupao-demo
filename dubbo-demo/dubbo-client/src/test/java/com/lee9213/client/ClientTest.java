package com.lee9213.client;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.lee9213.service.IHelloService;
import org.junit.Test;

import java.sql.Driver;
import java.util.ServiceLoader;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-16 1:32
 */
public class ClientTest {

    @Test
    public void consumer() {

        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("lee9213-dubbo-client");


        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://192.168.0.18:2181");


        ReferenceConfig<IHelloService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setInterface(IHelloService.class);
        referenceConfig.setProtocol("dubbo");
        referenceConfig.setVersion("1.0");


        IHelloService helloService = referenceConfig.get();
        String message = helloService.hello("mic");
        System.out.println(message);


        ServiceLoader serviceLoader = ServiceLoader.load(Driver.class);
    }
}
