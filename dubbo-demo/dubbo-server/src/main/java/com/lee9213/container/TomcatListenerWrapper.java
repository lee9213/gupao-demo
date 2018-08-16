package com.lee9213.container;

import com.alibaba.dubbo.container.Container;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-16 10:44
 */
public class TomcatListenerWrapper implements Container {


    private Container container;

    public TomcatListenerWrapper(Container container) {
        this.container = container;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
