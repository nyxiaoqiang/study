package testutil;

import java.util.concurrent.CountDownLatch;

//计数器，模拟等6个学生离开班级，才往下关门
public class TestCountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(7);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"撤离完成");
                countDownLatch.countDown(); //数量-1
            }).start();
        }
        countDownLatch.await();
        System.out.println("关门");
    }
}
