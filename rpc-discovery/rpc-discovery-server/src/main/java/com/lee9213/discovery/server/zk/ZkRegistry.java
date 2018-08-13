package com.lee9213.discovery.server.zk;

import com.lee9213.discovery.common.zk.ZkClient;
import com.lee9213.discovery.server.anno.RpcAnnotation;
import org.apache.zookeeper.data.Stat;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-13 1:14
 */
public class ZkRegistry implements IRegistry {

    public static Map<String, Object> HANDLER_MAP = new ConcurrentHashMap<>();

    @Override
    public void registry(Object service, String address) {
        RpcAnnotation rpcAnnotation = service.getClass().getAnnotation(RpcAnnotation.class);

        String serviceName = rpcAnnotation.name().getName();
        HANDLER_MAP.put(serviceName + "-" + rpcAnnotation.version(), service);

        StringBuilder path = new StringBuilder();
        path.append("/").append(serviceName).append("/").append(address);
        try {
            Stat stat = ZkClient.getInstance().checkExists(path.toString());
            if (stat == null) {
                ZkClient.getInstance().create(path.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
