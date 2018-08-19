package com.lee9213.annotation.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lee9213.service.IUserService;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-14 22:56
 */
@Service
public class ShopServiceImpl implements IUserService {
    @Override
    public String hello(String message) {
        return "hello, i'm " + message;
    }
}
