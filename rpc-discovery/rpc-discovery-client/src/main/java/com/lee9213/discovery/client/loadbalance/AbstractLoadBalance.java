package com.lee9213.discovery.client.loadbalance;

import java.util.List;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-13 10:58
 */
public abstract class AbstractLoadBalance implements LoadBalance {

    @Override
    public String getHost(List<String> address) {
        if (address.isEmpty()) {
            return null;
        }
        if(address.size() == 1) {
            return address.get(0);
        }
        return doGetHost(address);
    }

    public abstract String doGetHost(List<String> adress);
}
