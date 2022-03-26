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
public class TestCondition {
    public static void main(String[] args) {
        Data1 data = new Data1();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printA();
            }
        },"A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printB();
            }
        },"B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printC();
            }
        },"C").start();
    }
}

//等待  通知  业务
class Data1{
    private int num = 1;  //1A  //2B  //3C

    final Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    public void printA(){
        lock.lock();
        try {
            while (num !=1){
                //等待
                condition1.await();
            }
            num = 2;
            System.out.println(Thread.currentThread().getName()+"==> AAA");
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB(){
        lock.lock();
        try {
            while (num != 2){
                //等待
                condition2.await();
            }
            num = 3;
            System.out.println(Thread.currentThread().getName()+"==> BBB");
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC(){
        lock.lock();
        try {
            while (num != 3){
                //等待
                condition3.await();
            }
            num = 1;
            System.out.println(Thread.currentThread().getName()+"==> CCC");
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}