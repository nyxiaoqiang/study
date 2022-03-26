package cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CasDemo {
    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger(2020);
        // public final boolean compareAndSet(int expect, int update)
        // 如果期望值达到了，那么就更新，否则就不更新。CAS其实是CPU的并发原语，下面的举例其实是java层面的cas
        System.out.println(integer.compareAndSet(2020, 2021));//true
        System.out.println(integer.compareAndSet(2020, 2021));//false
        System.out.println(integer.get());//2021
    }
}
