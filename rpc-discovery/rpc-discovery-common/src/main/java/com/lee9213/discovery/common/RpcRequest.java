package com.lee9213.discovery.common;

import java.io.Serializable;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-13 0:58
 */
public class RpcRequest implements Serializable {

    private static final long serialVersionUID = 9109962937401141979L;
    private String className;
    private String methodName;
    private Object[] parameters;
    private String version;


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
