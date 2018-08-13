package com.lee9213.discovery.server;

import com.google.common.collect.Queues;
import com.lee9213.discovery.server.rpc.ProcessHandler;
import com.lee9213.discovery.server.zk.IRegistry;
import com.lee9213.discovery.server.zk.ZkRegistry;
import com.lee9213.service.IHelloService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-13 0:20
 */
public class DiscoveryServer {

    private static ExecutorService executorService = new ThreadPoolExecutor(2, 16, 60, TimeUnit.SECONDS, Queues.newLinkedBlockingQueue());

    public void register(IHelloService helloService, String address) {

        IRegistry zkRegistry = new ZkRegistry();
        zkRegistry.registry(helloService, address);

        try (ServerSocket serverSocket = new ServerSocket(Integer.parseInt(address.split(":")[1]))) {
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.execute(new ProcessHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
