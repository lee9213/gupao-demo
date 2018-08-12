package com.lee9213.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-12 17:00
 */
public class CuratorDemo {
    CuratorFramework framework;

    @Before
    public void before() {
        framework = CuratorFrameworkFactory.builder()
                .connectString("192.168.0.18:2181,192.168.0.18:2182,192.168.0.18:2183")
                .sessionTimeoutMs(2000)
                .connectionTimeoutMs(2000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .namespace("lee9213")
                .build();

        framework.start();
    }

    @After
    public void after() {
        framework.close();
    }

    @Test
    public void test() {

        try {
            framework.create().creatingParentContainersIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .forPath("/curator/node1", "test".getBytes());

            Stat stat = new Stat();
            byte[] bytes = framework.getData().storingStatIn(stat).forPath("/curator/node1");
            System.out.println("getData1 : " + new String(bytes));


            stat = framework.setData().withVersion(stat.getVersion()).forPath("/curator/node1", "test2".getBytes());

            bytes = framework.getData().storingStatIn(stat).forPath("/curator/node1");
            System.out.println("getData2 : " + new String(bytes));


            Thread.sleep(3000);

            framework.delete().deletingChildrenIfNeeded().forPath("/curator/node1");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testModify() {
        try {
            framework.create().creatingParentContainersIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .forPath("/curator/node1", "test".getBytes());
            framework.setData().forPath("/curator/node1","1test11".getBytes());

            framework.delete().deletingChildrenIfNeeded().forPath("/curator/node1");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNodeCacheWatch() {
        NodeCache nodeCache = new NodeCache(framework, "/curator/node1", false);
        NodeCacheListener listener = () -> System.out.println(nodeCache.getCurrentData().getPath());
        nodeCache.getListenable().addListener(listener);
        try {
            nodeCache.start();
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testPathChildrenCacheWatch() {
        PathChildrenCache nodeCache = new PathChildrenCache(framework, "/curator/node1", false);
        PathChildrenCacheListener listener = (curatorFramework, pathChildrenCacheEvent) -> {
            System.out.println(pathChildrenCacheEvent.getData().getPath());
        };
        nodeCache.getListenable().addListener(listener);
        try {
            nodeCache.start();
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTreeWatch() {
        TreeCache nodeCache = new TreeCache(framework, "/curator/node1");
        TreeCacheListener listener = (curatorFramework, treeCacheEvent) ->
                System.out.println(treeCacheEvent.getType() + "==>" + treeCacheEvent.getData().getPath());
        nodeCache.getListenable().addListener(listener);
        try {
            nodeCache.start();
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
