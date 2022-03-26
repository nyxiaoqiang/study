package testcollection;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestList {
    public static void main(String[] args) {
        /**
         * list = new ArrayList<>(); 会导致并发修改异常
         *      java.util.ConcurrentModificationException
         * 解决方案：
         * 1、list = new Vector<>();  Vector有synchronized修饰是线程安全的
         * 2、list = Collections.synchronizedList(new ArrayList<>()); 使用工具类转换
         * 3、list = new CopyOnWriteArrayList<>(); JUC的解决方案
         */
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
