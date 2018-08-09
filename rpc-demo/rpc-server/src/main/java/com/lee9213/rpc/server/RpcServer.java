package com.lee9213.rpc.server;


import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-07 20:44
 */
public class RpcServer {

    private ExecutorService executorService = new ThreadPoolExecutor(1, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<>());
    private int port;

    public RpcServer(int port) {
        this.port = port;
    }

    public void publisher(Object service) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.execute(new ProcessorHandler(service, socket));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
