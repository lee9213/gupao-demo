package com.lee9213.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-09 23:57
 */
public class ZKAuthDemo extends ZkTestParent{

    @Test
    public void test() {
        try {
//            Id id = new Id("digest", DigestAuthenticationProvider.generateDigest("lee9213:lee9213"));
            Id id = new Id("world", "anyone");
            ACL acl = new ACL(ZooDefs.Perms.ALL, id);
            List<ACL> acls = Collections.singletonList(acl);

            String path = zooKeeper.create("/lee9213/node8", "test1".getBytes(), acls, CreateMode.PERSISTENT);
            System.out.printf("Zookeeper create node : %s", path);
            System.out.println();
            Stat stat;


            stat = zooKeeper.exists(path, watchedEvent -> {
                System.out.printf("Zookeeper Exists Watcher,EventType: %s, Path: %s", watchedEvent.getType(), watchedEvent.getPath());
                System.out.println();
            });

            byte[] data;
            data = zooKeeper.getData(path, true, stat);
            System.out.printf("Zookeeper getData1 : %s", new String(data));
            System.out.println();


            data = zooKeeper.getData(path, watchedEvent -> {
                System.out.printf("Zookeeper getData Watcher,EventType: %s, Path: %s", watchedEvent.getType(), watchedEvent.getPath());
                System.out.println();
            }, stat);
            System.out.printf("Zookeeper getData2 : %s", new String(data));
            System.out.println();


            zooKeeper.getChildren("/lee9213", watchedEvent -> {
                System.out.printf("Zookeeper Children Watcher,EventType: %s, Path: %s", watchedEvent.getType(), watchedEvent.getPath());
                System.out.println();
            });

            stat = zooKeeper.setData(path, "test2".getBytes(), stat.getVersion());

            zooKeeper.delete(path, stat.getVersion());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        } finally {
            try {
                if (zooKeeper != null) {
                    zooKeeper.close();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void zkTransactionTest(){

        Transaction transaction = zooKeeper.transaction();
        transaction.create("/lee9213/transaction-node1", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        transaction.create("/lee9213/transaction-node2", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        transaction.delete("/lee9213/transaction-node1", 0);
        transaction.check("/lee9213/transaction-node2", 0);
        transaction.setData("/lee9213/transaction-node2", "test".getBytes(), 0);
        try {
            transaction.commit();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void zkMultiTest(){

        List<Op> ops = new ArrayList<>();
        ops.add(Op.create("/lee9213/multi", "data1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT.toFlag()));
        ops.add(Op.create("/lee9213/multi/node1", "data1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT.toFlag()));
        ops.add(Op.create("/lee9213/multi/node2", "data1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT.toFlag()));
        try {
            zooKeeper.multi(ops).forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void zkSyncTest(){

        // 同步所有的更新操作
        zooKeeper.sync("/lee9213/node1", (i, s, o) -> {

        },null);

    }
}
