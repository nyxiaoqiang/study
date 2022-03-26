package testblockqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class TestSynchronousQueue {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new SynchronousQueue<String>();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+" put 1");
                queue.put("1");
                System.out.println(Thread.currentThread().getName()+" put 2");
                queue.put("2");
                System.out.println(Thread.currentThread().getName()+" put 3");
                queue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"get "+queue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"get "+queue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"get "+queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
