package testpool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 生产中，不能通过Executors创建线程池，使用不当会造成OOM
 * 下面创建线程池的方式，是正确的生产中创建的方式
 */
public class CreateThreadPool {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());//队列满了,再来请求，抛出异常

        try {
            for (int i = 0; i < 8; i++) {
                threadPoolExecutor.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+" Ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //线程池使用完毕，需要手动关闭
            threadPoolExecutor.shutdown();
        }
    }
}
