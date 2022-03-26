package testreadwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock是一个接口
 * 实现类只有：ReentrantReadWriteLock
 * 案例：编写一个细粒度的缓存（可以同时读，但只能有一个线程写）
 */
public class TestReadWriteLock {
    public static void main(String[] args) {
        MyCacheLock cache = new MyCacheLock();
        for (int i = 0; i < 5; i++) {
            final String temp = String.valueOf(i);
            new Thread(() -> {
                cache.put(temp,temp);
            },String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++) {
            final String temp = String.valueOf(i);
            new Thread(() -> {
                cache.get(temp);
            },String.valueOf(i)).start();
        }
    }
}

class MyCacheLock{
    private volatile Map<String,String> cache = new HashMap<>();
    //读写锁，可以更加细粒度的控制，提高效率
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();


    public void put(String key,String value){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"开始写入");
            cache.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key){
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"开始读取");
            cache.get(key);
            System.out.println(Thread.currentThread().getName()+"读取完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}