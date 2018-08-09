package com.lee9213.rpc.client;


import com.lee9213.request.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-07 21:31
 */
public class RpcInvocationHandler implements InvocationHandler {

    private String host;
    private int port;

    public RpcInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        RpcRequest request = new RpcRequest();
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParameters(args);

        RpcTransport rpcTransport = new RpcTransport(host, port);
        return rpcTransport.send(request);
    }
}
