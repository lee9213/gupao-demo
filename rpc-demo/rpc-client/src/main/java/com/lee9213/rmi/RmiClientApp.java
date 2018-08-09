package com.lee9213.rmi;

import com.lee9213.rmi.service.IUserService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-08 13:54
 */
public class RmiClientApp {

    public static void main(String[] args) {

        try {
            IUserService  userService = (IUserService) Naming.lookup("rmi://127.0.0.1/userService");
            System.out.println(userService.sayHello("what are your from?"));


        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
