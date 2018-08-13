package com.lee9213.discovery.client.proxy;

import java.lang.reflect.Proxy;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-13 10:34
 */
public class RpcProxy {

    public static <T> T newInstance(Class clazz, String version) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new RpcProxyInvocationHandler(version));
    }
}
