package org.tylor.tutorial.concurrency;

/**
 * Tylor 2020/10/25
 */
public class Sequence {
   int value;
   public synchronized int getNext(){
       return value++;
   }
}
