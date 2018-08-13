package com.lee9213.discovery.client.loadbalance;

import java.util.List;
import java.util.Random;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-13 11:00
 */
public class RandomLoadBalance extends AbstractLoadBalance {

    @Override
    public String doGetHost(List<String> adress) {
        int size = adress.size();
        return adress.get(new Random().nextInt(size));
    }
}
