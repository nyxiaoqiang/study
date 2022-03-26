package testforkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class TestForkJoin {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
/*        test1();
        test2();
        test3();*/
        test4();

    }

    /**
     * 使用传统的for循环做1加到10亿
     */
    private static void test1(){
        long start = System.currentTimeMillis();
        Long sum = 0L;
        for (Long i = 1L; i <= 10_0000_0000; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("sum="+sum+" 时间："+(end-start));//10490
    }

    /**
     * 使用forkjoin框架实现 1加到10亿
     */
    private static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> testForkJoinDemo = new TestForkJoinDemo(0L,10_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(testForkJoinDemo);
        Long sum = submit.get();
        long end = System.currentTimeMillis();
        System.out.println("sum="+sum+" 时间："+(end-start)); //9688
    }

    /**
     * 使用Stream流实现 1加到10亿
     */
    private static void test3(){
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0L, 10_0000_0000L).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("sum="+sum+" 时间："+(end-start)); //389
    }

    /**
     * 对比for循环和Stream流对1000万个随机数相加的耗时对比
     */
    private static void test4(){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10_0000_000; i++) {
            list.add((int)(Math.random()*1000));
        }
        LongStream longStream = list.stream().mapToLong((i) -> i);
        long start2 = System.currentTimeMillis();
        long sum2 = 0L;
        for (int i = 0; i < list.size(); i++) {
            sum2 = sum2 + list.get(i);
        }
        long end2 = System.currentTimeMillis();
        System.out.println(sum2+" =====> "+(end2 - start2));//平均29  205
        long start = System.currentTimeMillis();
        long sum = longStream.sum();

        long end = System.currentTimeMillis();
        System.out.println(sum+" =====> "+(end - start));//平均75   266
    }
}
