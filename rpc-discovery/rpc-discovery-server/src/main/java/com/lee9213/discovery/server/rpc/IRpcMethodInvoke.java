package com.lee9213.discovery.server.rpc;

import com.lee9213.discovery.common.RpcRequest;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-13 9:54
 */
public interface IRpcMethodInvoke {

    Object invoke(RpcRequest request);
}
