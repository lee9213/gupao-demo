package com.lee9213.juc.chapter1;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-26 10:10
 */
public class SelectProcessor extends Thread implements Processor {
    LinkedBlockingQueue<Request> queue = new LinkedBlockingQueue();

    private Processor nextProcesor;

    public SelectProcessor(Processor nextProcesor) {
        super("SelectThread");
        this.nextProcesor = nextProcesor;
    }

    @Override
    public void processor(Request request) {
        this.queue.add(request);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Request request = queue.take();
                System.out.println("select: " + request.getName());
                if(nextProcesor != null) {
                    nextProcesor.processor(request);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
