package org.tylor.tutorial.concurrency;

/**
 * Tylor 2020/11/6
 */
public class NoVisibility {
    static boolean ready;
    static int number;

    private static class ReaderThread extends Thread{
        public void run() {
            while (!ready){
                Thread.yield();
            }
            if (number!=42){
                System.out.println(number);
            }

        }
    }

    public static void main(String[] args) {

        //实际观测与书中不一致，没发现线程一直挂起的情况，也没有输出0的情况
        for (int i = 0; i < 1000; i++) {
            new ReaderThread().start();
            number = 42;
            ready = true;
        }
    }

}
