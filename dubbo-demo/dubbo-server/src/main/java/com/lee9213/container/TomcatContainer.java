package com.lee9213.container;

import com.alibaba.dubbo.container.Container;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-14 21:42
 */
public class TomcatContainer implements Container {


    @Override
    public void start() {
        System.out.println("tomcat container start");
    }

    @Override
    public void stop() {
        System.out.println("tomcat container stop");
    }
}
