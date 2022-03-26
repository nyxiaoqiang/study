package cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class CasLock {
    AtomicReference<Thread> lock = new AtomicReference<>();
    public int sum = 0;
    ReentrantLock reentrantLock = new ReentrantLock();

    public void lock(){
        Thread thread = Thread.currentThread();
        while (!lock.compareAndSet(null,thread)){
            //空转
        }
    }

    public void unLock(){
        Thread thread = Thread.currentThread();
        while (!lock.compareAndSet(thread,null)){
            //空转
        }
    }

    public static void main(String[] args) {
        CasLock cas = new CasLock();
        new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                cas.method2();
            }
        },"thread1").start();

        new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                cas.method2();
            }
        },"thread2").start();

        while (Thread.activeCount() > 2){
            Thread.yield();
        }
    }

    public void method1(){
        try {
            TimeUnit.NANOSECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            reentrantLock.lock();
            add();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }

        System.out.println(sum);
    }

    public void add(){
        try {
            reentrantLock.lock();
            sum++;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }

    }

    public void method2(){
        try {
            TimeUnit.NANOSECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock();
        add2();
        unLock();
        System.out.println(sum);
    }

    public void add2(){
       lock();
       reentrantLock.lock();
       unLock();
    }
}

