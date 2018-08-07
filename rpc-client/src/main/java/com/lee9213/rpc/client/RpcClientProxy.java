package com.lee9213.rpc.client;

import java.lang.reflect.Proxy;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-07 21:29
 */
public class RpcClientProxy {

    public static <T> T newProxy(Class service,String host,int port) {
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class[]{service}, new RpcInvocationHandler(host,port));
    }
}
