package com.lee9213.rmi.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-08 13:49
 */
public interface IUserService extends Remote {

    String sayHello(String message)  throws RemoteException;
}
