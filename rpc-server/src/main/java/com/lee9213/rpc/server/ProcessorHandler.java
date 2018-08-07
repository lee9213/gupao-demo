package com.lee9213.rpc.server;

import com.lee9213.request.RpcRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-07 23:07
 */
public class ProcessorHandler implements Runnable {

    private Object service;
    private Socket socket;

    public ProcessorHandler(Object service, Socket socket) {
        this.service = service;
        this.socket = socket;
    }

    @Override
    public void run() {
        try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {

            RpcRequest rpcRequest = (RpcRequest) ois.readObject();

            RpcMethodInvoke rpcMethodInvoke = new RpcMethodInvoke();
            Object result = rpcMethodInvoke.invoke(service,rpcRequest);
            oos.writeObject(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
