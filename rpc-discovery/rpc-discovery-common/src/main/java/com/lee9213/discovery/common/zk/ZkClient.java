package com.lee9213.discovery.common.zk;

import com.lee9213.discovery.common.constant.Constant;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-13 0:24
 */
public final class ZkClient {

    private CuratorFramework FRAMEWORK;

    private ZkClient() {
        FRAMEWORK = CuratorFrameworkFactory.builder()
                .connectString(Constant.ZK_ADDRESS)
                .sessionTimeoutMs(Constant.ZK_SESSION_TIMEOUT)
                .connectionTimeoutMs(Constant.ZK_CONNECTION_TIMEOUT)
                .retryPolicy(new ExponentialBackoffRetry(3000, 10))
                .namespace(Constant.ZK_NAMESPACE)
                .build();
        FRAMEWORK.start();
    }

    private static class ZkClientHolder {
        private final static ZkClient ZK_CLIENT = new ZkClient();
    }

    public static ZkClient getInstance() {
        return ZkClientHolder.ZK_CLIENT;
    }

    public Stat checkExists(String path) {
        try {
            return FRAMEWORK.checkExists().forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String create(String path) {
        try {
            return FRAMEWORK.create().creatingParentContainersIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<String> getChildren(String path) {
        try {
            return FRAMEWORK.getChildren().forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
