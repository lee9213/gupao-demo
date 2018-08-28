package com.lee9213.juc.chapter1;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-25 18:10
 */
public class Request {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                '}';
    }
}
