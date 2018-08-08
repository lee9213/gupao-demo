package com.lee9213.rmi.service.impl;

import com.lee9213.rmi.service.IUserService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-08 13:50
 */
public class UserServiceImpl extends UnicastRemoteObject implements IUserService {


    public UserServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHello(String message) throws RemoteException {
        return "hello, " + message;
    }
}
