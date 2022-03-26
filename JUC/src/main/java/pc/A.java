package pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程之间的通讯问题：生产者和消费者问题   等待唤醒，通知唤醒
 * 线程交替执行  A  B操作同一个变量
 *      A num+1
 *      B num-1
 */
public class A {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"生产A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"消费B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"生产C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"消费D").start();
    }
}

//等待  通知  业务
class Data{
    private int num = 0;

    final Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    //+1
    public  void increment() throws InterruptedException {
        lock.lock();

        try {
            while (num > 0){
                //等待
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName()+"生产=>"+num);
            //通知其他线程
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }


    //-1
    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (num <= 0){
                //等待
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName()+"消费=>"+num);
            //通知其他线程
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}