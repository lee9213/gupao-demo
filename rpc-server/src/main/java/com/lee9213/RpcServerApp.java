package com.lee9213;

import com.lee9213.rpc.server.RpcServer;
import com.lee9213.rpc.service.impl.HelloServiceImpl;
import com.lee9213.service.IHelloService;

/**
 * Hello world!
 *
 */
public class RpcServerApp {
    public static void main( String[] args ) {
//        List<String> urls = new ArrayList<>();
//
//
//        urls.add("1");urls.add("1");urls.add("1");urls.add("1");urls.add("1");urls.add("1");urls.add("1");
//        urls.add("2");urls.add("2");urls.add("2");urls.add("2");urls.add("2");
//        urls.add("3");urls.add("3");urls.add("3");urls.add("3");urls.add("3");urls.add("3");urls.add("3");urls.add("3");
//        urls.add("4");urls.add("4");urls.add("4");
//        urls.add("5");urls.add("5");urls.add("5");urls.add("5");urls.add("5");urls.add("5");urls.add("5");
//        urls.add("6");urls.add("6");urls.add("6");urls.add("6");
//
//
//        urls.stream().collect(Collectors.groupingBy(item -> item)).entrySet().stream()
//                .sorted((o1, o2) -> o1.getValue().size() >= o2.getValue().size() ? -1 : 1)
//                .forEach(item -> System.out.println(item.getKey()+ "=" + item.getValue().size()));


        IHelloService helloService = new HelloServiceImpl();
        RpcServer rpcServer = new RpcServer(9999);
        rpcServer.publisher(helloService);

    }
}
