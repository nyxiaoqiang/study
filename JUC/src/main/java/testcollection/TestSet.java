package testcollection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

public class TestSet {
    public static void main(String[] args) {
        /**
         * set = new HashSet<>(); 会导致并发修改异常
         *      java.util.ConcurrentModificationException
         * 解决方案：
         * 1、set = Collections.synchronizedSet(new HashSet<>()); 使用工具类转换
         * 2、set = new CopyOnWriteArraySet<>(); JUC的解决方案
         */
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }
}
