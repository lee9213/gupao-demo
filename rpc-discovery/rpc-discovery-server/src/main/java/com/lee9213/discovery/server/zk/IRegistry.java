package com.lee9213.discovery.server.zk;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-13 1:15
 */
public interface IRegistry {

    void registry(Object service, String address);
}
