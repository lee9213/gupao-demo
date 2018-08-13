package com.lee9213.discovery.server.rpc;

import com.lee9213.discovery.common.RpcRequest;
import com.lee9213.discovery.server.zk.ZkRegistry;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-13 0:59
 */
public class RpcMethodInvokeImpl implements IRpcMethodInvoke {

    @Override
    public Object invoke(RpcRequest request) {
        String className = request.getClassName();
        String version = request.getVersion();
        if (version != null && !"".equals(version)) {
            className = className + "-" + version;
        }
        Object object = ZkRegistry.HANDLER_MAP.get(className);

        Object[] parameters = request.getParameters();
        try {
            Method method;
            if (parameters != null && parameters.length > 0) {
                Class[] types = new Class[parameters.length];
                for (int i = 0; i < parameters.length; i++) {
                    types[i] = parameters[i].getClass();
                }

                method = object.getClass().getMethod(request.getMethodName(), types);

            } else {
                method = object.getClass().getMethod(request.getMethodName());
            }

            return method.invoke(object, parameters);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
