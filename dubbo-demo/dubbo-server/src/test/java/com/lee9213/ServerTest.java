package com.lee9213;

import com.alibaba.dubbo.common.utils.AtomicPositiveInteger;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.lee9213.service.IHelloService;
import com.lee9213.service.impl.HelloServiceImpl;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

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

    private final ConcurrentMap<String, AtomicPositiveInteger> sequences = new ConcurrentHashMap<String, AtomicPositiveInteger>();

    private final ConcurrentMap<String, AtomicPositiveInteger> weightSequences = new ConcurrentHashMap<String, AtomicPositiveInteger>();

    @Test
    public void testRound() {
        Map<String, Integer> map = new HashMap<>();
        map.put("service1", 1);
        map.put("service2", 2);
        map.put("service3", 3);
        map.put("service4", 4);


        List<String> invokers = new ArrayList<>();
        invokers.add("service1");
        invokers.add("service2");
        invokers.add("service3");
        invokers.add("service4");

        for (int j = 0; j < 10; j++) {
            String key = invokers.get(0);
            int length = invokers.size(); // 总个数
            int maxWeight = 0; // 最大权重
            int minWeight = Integer.MAX_VALUE; // 最小权重
            for (int i = 0; i < length; i++) {
                int weight = map.get(invokers.get(i));
                maxWeight = Math.max(maxWeight, weight); // 累计最大权重
                minWeight = Math.min(minWeight, weight); // 累计最小权重
            }
            if (maxWeight > 0 && minWeight < maxWeight) { // 权重不一样
                AtomicPositiveInteger weightSequence = weightSequences.get(key);
                if (weightSequence == null) {
                    weightSequences.putIfAbsent(key, new AtomicPositiveInteger());
                    weightSequence = weightSequences.get(key);
                }
                int currentWeight = weightSequence.getAndIncrement() % maxWeight;
                List<String> weightInvokers = new ArrayList<>();
                for (String serviceKey : invokers) { // 筛选权重大于当前权重基数的Invoker
                    if (map.get(serviceKey) > currentWeight) {
                        weightInvokers.add(serviceKey);
                    }
                }
                int weightLength = weightInvokers.size();
                if (weightLength == 1) {
                    System.out.println(weightInvokers.get(0));
                } else if (weightLength > 1) {
                    invokers = weightInvokers;
                    length = invokers.size();
                }
            }
            AtomicPositiveInteger sequence = sequences.get(key);
            if (sequence == null) {
                sequences.putIfAbsent(key, new AtomicPositiveInteger());
                sequence = sequences.get(key);
            }
            // 取模轮循
            System.out.println(invokers.get(sequence.getAndIncrement() % length));
        }
    }
}
