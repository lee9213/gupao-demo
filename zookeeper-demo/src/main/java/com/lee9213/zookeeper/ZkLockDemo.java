package com.lee9213.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-12 20:27
 */
public class ZkLockDemo implements Lock, Watcher {

    private ZooKeeper zooKeeper;
    private String ROOT_LOCK = "/lee9213/locks";
    private String CURRENT_LOCK;
    private String WAIT_LOCK;
    private CountDownLatch countDownLatch;

    public ZkLockDemo() {

        try {
            zooKeeper = new ZooKeeper("192.168.0.18:2181,192.168.0.18:2182,192.168.0.18:2183", 1000, this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean tryLock() {
        try {
            CURRENT_LOCK = zooKeeper.create(ROOT_LOCK + "/", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            List<String> children = zooKeeper.getChildren(ROOT_LOCK, false);
            TreeSet<String> treeSet = new TreeSet<>();
            children.forEach(lock -> treeSet.add(ROOT_LOCK + "/" + lock));
            String lock = treeSet.first();

            if (CURRENT_LOCK.equals(lock)) {
                return true;
            }
            SortedSet<String> strings = treeSet.headSet(CURRENT_LOCK);
            if (!strings.isEmpty()) {
                WAIT_LOCK = strings.last();
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void process(WatchedEvent event) {
        if (countDownLatch != null) {
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + "获取锁成功" + CURRENT_LOCK);
        }
    }

    @Override
    public void lock() {
        if (tryLock()) {
            System.out.println(Thread.currentThread().getName() + "获取锁" + CURRENT_LOCK + "成功");
        } else {
            try {
                Stat exists = zooKeeper.exists(WAIT_LOCK, true);
                if (exists != null) {
                    System.out.println(Thread.currentThread().getName() + "等待锁" + WAIT_LOCK + "释放");
                    countDownLatch = new CountDownLatch(1);
                    countDownLatch.await();
                }
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }


    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        try {
            Stat stat = zooKeeper.exists(CURRENT_LOCK, false);
            zooKeeper.delete(CURRENT_LOCK, stat.getVersion());
            zooKeeper.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ZkLockDemo zkLockDemo = new ZkLockDemo();
                zkLockDemo.lock();
            }, "Thread" + i).start();
            countDownLatch.countDown();
        }
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
