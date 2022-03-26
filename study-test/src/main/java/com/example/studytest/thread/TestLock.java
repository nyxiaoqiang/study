package com.example.studytest.thread;

import java.util.concurrent.locks.ReentrantLock;

public class TestLock implements Runnable{
    int ticketNums = 10;
    ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        TestLock testLock = new TestLock();
        new Thread(testLock,"线程一").start();
        new Thread(testLock,"线程二").start();
        new Thread(testLock,"线程三").start();
    }

    @Override
    public void run() {
       while (true){
           try {
                lock.lock();
               if(ticketNums > 0){
                   System.out.println(Thread.currentThread().getName()+"购买了"+ticketNums);
                   ticketNums--;
               }else {
                   break;
               }
           }finally {
                lock.unlock();
           }
       }
    }
}

