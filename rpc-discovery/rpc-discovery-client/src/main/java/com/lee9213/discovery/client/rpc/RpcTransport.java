package com.lee9213.discovery.client.rpc;

import com.lee9213.discovery.client.loadbalance.LoadBalance;
import com.lee9213.discovery.client.loadbalance.RandomLoadBalance;
import com.lee9213.discovery.common.RpcRequest;
import com.lee9213.discovery.common.zk.ZkClient;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-13 10:39
 */
public class RpcTransport {

    public Object doTransport(RpcRequest request) {
        List<String> children = ZkClient.getInstance().getChildren("/" + request.getClassName());

        LoadBalance loadBalance = new RandomLoadBalance();
        String address = loadBalance.getHost(children);
        String[] host = address.split(":");
        System.out.printf("ip:%s, ", address);
        try (Socket socket = new Socket(host[0], Integer.parseInt(host[1]));
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream())) {

            objectOutputStream.writeObject(request);

            return objectInputStream.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
