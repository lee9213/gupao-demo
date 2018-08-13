package com.lee9213.discovery.client.loadbalance;

import java.util.List;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-13 10:54
 */
public interface LoadBalance {


    String getHost(List<String> address);
}
