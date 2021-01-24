package org.tylor.tutorial.concurrency;

import java.util.ArrayList;
import java.util.List;

/**
 * Tylor 2020/11/15
 */
public class WaitNodifyDemo {

    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(()->{
            try {
                for (int i = 0; i < 50 ; i++) {
                    synchronized (lock) {
                        lock.wait();
                        System.out.println(i*2+2);
                        lock.notify();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(()->{
            for (int i = 0; i < 50 ; i++) {
                synchronized (lock) {
                    try {
                        System.out.println(i*2+1);
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
            synchronized (lock){
                lock.notifyAll();
            }
        });
        thread1.start();
        Thread.sleep(100);
        thread2.start();
//



    }

}
