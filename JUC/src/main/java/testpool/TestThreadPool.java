package testpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThreadPool {
    public static void main(String[] args) {
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();//单个线程    pool-1-thread-1 ok
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);//最大可以有五个线程 pool-1-thread-1 ok ..... pool-1-thread-5 ok
        ExecutorService threadPool = Executors.newCachedThreadPool();//可伸缩，最大可以有21亿个线程（显然不合理）pool-1-thread-1 ok ... pool-1-thread-10 ok
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+" ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //线程池使用完，程序结束，需要关闭线程池。
            threadPool.shutdown();
        }
    }
}
