package com.lee9213.rpc.server;

import com.lee9213.request.RpcRequest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-07 21:07
 */
public class RpcMethodInvoke {

    public Object invoke(Object object, RpcRequest rpcRequest) {

        Object result = null;
        try {
            Object[] parameters = rpcRequest.getParameters();
            String methodName = rpcRequest.getMethodName();
            if (parameters != null) {
                Class[] types = new Class[parameters.length];
                for (int i = 0; i < parameters.length; i++) {
                    types[i] = parameters[i].getClass();
                }
                Method method = object.getClass().getMethod(methodName, types);
                result = method.invoke(object, parameters);
            } else {
                Method method = object.getClass().getMethod(methodName);
                result = method.invoke(object);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }
}
