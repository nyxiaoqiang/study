package testcollection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class TestMap {
    public static void main(String[] args) throws InterruptedException {
        /**
         *  map = new HashMap<>(16); 会导致并发修改异常
         *      java.util.ConcurrentModificationException
         * 解决方案：
         * 1、map = new Hashtable<>();
         * 2、map = Collections.synchronizedMap(new HashMap<>(16));
         * 3、map = new ConcurrentHashMap<>(16); JUC的解决方案
         */
        Map<String,String> map = new ConcurrentHashMap<>(16);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,5));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
        TimeUnit.SECONDS.sleep(1);
        System.out.println(map);
    }
}
