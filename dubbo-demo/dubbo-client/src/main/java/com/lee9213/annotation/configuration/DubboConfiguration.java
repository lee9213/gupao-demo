package com.lee9213.annotation.configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-18 21:34
 */
@Configuration
public class DubboConfiguration {


    @Bean
    public ApplicationConfig applicationConfig(){
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("dubbo-client");
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registryConfig(){
        RegistryConfig registryConfig = new RegistryConfig();

        registryConfig.setAddress("zookeeper://192.168.0.18:2181");
        registryConfig.setProtocol("dubbo");

        return registryConfig;
    }
}
