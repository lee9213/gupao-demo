package com.lee9213.juc.chapter1;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-25 19:03
 */
public class InterruptDemo {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().isInterrupted());
                Thread.interrupted();
                System.out.println(Thread.currentThread().isInterrupted());
            }
            System.out.println(Thread.currentThread().isInterrupted());
        });
        thread.start();
        Thread.sleep(10);
        thread.interrupt();
    }
}
