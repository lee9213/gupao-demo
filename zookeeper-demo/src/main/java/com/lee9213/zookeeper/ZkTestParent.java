package com.lee9213.zookeeper;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Before;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-11 23:37
 */
public class ZkTestParent {

    protected final CountDownLatch countDownLatch = new CountDownLatch(1);
    protected ZooKeeper zooKeeper;

    @Before
    public void before(){
        try {
            zooKeeper = new ZooKeeper("192.168.0.18:2181,192.168.0.18:2182,192.168.0.18:2183", 100000, watchedEvent -> {
                System.out.printf("Global Watcher, EventType: %s, Path: %s", watchedEvent.getType(), watchedEvent.getPath());
                System.out.println();
                if (watchedEvent.getState() == Watcher.Event.KeeperState.SyncConnected) {
                    countDownLatch.countDown();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(zooKeeper.getState());
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(zooKeeper.getState());
    }
}
