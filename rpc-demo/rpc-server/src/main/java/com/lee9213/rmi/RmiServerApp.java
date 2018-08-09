package com.lee9213.rmi;

import com.lee9213.rmi.service.IUserService;
import com.lee9213.rmi.service.impl.UserServiceImpl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-08 13:48
 */
public class RmiServerApp {


    public static void main(String[] args) {

        try {
            IUserService userService = new UserServiceImpl();
            LocateRegistry.createRegistry(1099);

            Naming.rebind("rmi://127.0.0.1/userService",userService);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
