package com.lee9213.juc.chapter1;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-26 10:15
 */
public class ProcessorDemo {

    public static void main(String[] args) {

        SelectProcessor select = new SelectProcessor(null);
        select.start();
        UpdateProcessor update = new UpdateProcessor(select);
        update.start();
        DeleteProcessor delete = new DeleteProcessor(update);
        delete.start();
        InsertProcessor insert = new InsertProcessor(delete);
        insert.start();

        Request request = new Request();
        request.setName("hello world");
        insert.processor(request);
    }
}
