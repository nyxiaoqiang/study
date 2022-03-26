package com.example.studytest.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class TestThreadPool {
    public static void main(String[] args) {
        //创建线程池
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            //执行
            executorService.execute(new MyThread1());
            executorService.execute(new MyThread1());
            executorService.execute(new MyThread1());
            //销毁
            executorService.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

 class MyThread1 implements  Runnable{
     @Override
     public void run() {
         for (int i = 0; i < 30; i++) {
             System.out.println("测试线程池"+i);
         }
     }
 }