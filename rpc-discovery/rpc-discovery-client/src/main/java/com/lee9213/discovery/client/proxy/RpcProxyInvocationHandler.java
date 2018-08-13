package com.lee9213.discovery.client.proxy;

import com.lee9213.discovery.client.rpc.RpcTransport;
import com.lee9213.discovery.common.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-13 10:38
 */
public class RpcProxyInvocationHandler implements InvocationHandler {

    private String version;

    public RpcProxyInvocationHandler(String version) {
        this.version = version;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest request = new RpcRequest();
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParameters(args);
        request.setVersion(version);

        RpcTransport transport = new RpcTransport();
        return transport.doTransport(request);
    }
}
