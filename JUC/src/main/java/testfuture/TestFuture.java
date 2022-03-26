package testfuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class TestFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //没有返回值的 runAsync
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("主线程");
        voidCompletableFuture.get();
        CompletableFuture<Integer> uCompletableFuture = CompletableFuture.supplyAsync(() -> {
            //int i = 10/0;
            return 1024;
        });
        Integer integer = uCompletableFuture.whenComplete((t, u) -> { //有返回值的时候怎么处理
            System.out.println(t); //正常的返回结果
            System.out.println(u); //错误信息
        }).exceptionally((e) -> { //遇到异常的时候怎么处理
            e.printStackTrace();
            return 404; //遇到错误的时候返回结果
        }).get();
        System.out.println(integer);
    }
}
