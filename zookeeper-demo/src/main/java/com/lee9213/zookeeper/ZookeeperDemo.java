package com.lee9213.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-09 16:51
 */
public class ZookeeperDemo {

    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zooKeeper = null;
        try {
            zooKeeper = new ZooKeeper("192.168.1.18:2181,192.168.1.18:2182,192.168.1.18:2183", 1000, watchedEvent -> {
                System.out.printf("Global Watcher, EventType: %s, Path: %s", watchedEvent.getType(), watchedEvent.getPath());
                System.out.println();
                if (watchedEvent.getState() == Watcher.Event.KeeperState.SyncConnected) {
                    countDownLatch.countDown();
                }
            });
            // 设置验证方式
//            zooKeeper.addAuthInfo("digest","lee9213:lee9213".getBytes());
            zooKeeper.addAuthInfo("ip","192.168.1.1/16".getBytes());

            System.out.println(zooKeeper.getState());
            countDownLatch.await();
            System.out.println(zooKeeper.getState());

//            Id id = new Id("digest", DigestAuthenticationProvider.generateDigest("lee9213:lee9213"));
            Id id = new Id("ip", "192.168.1.4");
            ACL acl = new ACL(ZooDefs.Perms.ALL, id);
            List<ACL> acls = Collections.singletonList(acl);
//            List<ACL> acls = ZooDefs.Ids.CREATOR_ALL_ACL;
//            List<ACL> acls = ZooDefs.Ids.OPEN_ACL_UNSAFE;

            String path = zooKeeper.create("/lee9213/node8", "test1".getBytes(), acls, CreateMode.PERSISTENT);
            System.out.printf("Zookeeper create node : %s", path);
            System.out.println();

//            zooKeeper.create("/lee9213", "test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, new AsyncCallback.StringCallback() {
//                @Override
//                public void processResult(int i, String s, Object param, String s1) {
//                    System.out.println(i + " " + s + " " + param + " " + s1);
//                }
//            },"param");


            Stat stat = new Stat();

            byte[] data = zooKeeper.getData(path, true, stat);
            System.out.printf("Zookeeper getData1 : %s", new String(data));
            System.out.println();


            data = zooKeeper.getData(path, watchedEvent -> {
                System.out.printf("Zookeeper getData Watcher,EventType: %s, Path: %s", watchedEvent.getType(), watchedEvent.getPath());
                System.out.println();
            }, stat);
            System.out.printf("Zookeeper getData2 : %s", new String(data));
            System.out.println();

            stat = zooKeeper.exists(path,watchedEvent -> {
                System.out.printf("Zookeeper Exists Watcher,EventType: %s, Path: %s", watchedEvent.getType(), watchedEvent.getPath());
                System.out.println();
            });

            zooKeeper.getChildren("/lee9213",watchedEvent -> {
                System.out.printf("Zookeeper Children Watcher,EventType: %s, Path: %s", watchedEvent.getType(), watchedEvent.getPath());
                System.out.println();
            });

            stat = zooKeeper.setData(path, "test2".getBytes(), stat.getVersion());



            zooKeeper.delete(path, stat.getVersion());




//            zooKeeper.multi();
//            zooKeeper.sync();
//            zooKeeper.transaction();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }  finally {
            try {
                if (zooKeeper != null) {
                    zooKeeper.close();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
