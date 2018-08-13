package com.lee9213.discovery.server.rpc;

import com.lee9213.discovery.common.RpcRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-13 0:55
 */
public class ProcessHandler implements Runnable {

    private Socket socket;

    public ProcessHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {

            RpcRequest request = (RpcRequest) objectInputStream.readObject();

            RpcMethodInvokeImpl rpcMethodInvoke = new RpcMethodInvokeImpl();
            Object result = rpcMethodInvoke.invoke(request);

            objectOutputStream.writeObject(result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
