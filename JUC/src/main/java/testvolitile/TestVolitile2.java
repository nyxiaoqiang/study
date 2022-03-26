package testvolitile;

import java.util.concurrent.atomic.AtomicInteger;

public class TestVolitile2 {
    private volatile static AtomicInteger sum = new AtomicInteger();

    private static void add(){
        sum.getAndIncrement();// // AtomicInteger + 1 方法， CAS
    }
    public static void main(String[] args) {
        //结果应该是10000
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }
        //保证上面的线程结束后，再获取结果（2：main gc）
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(sum);
    }
}
