package callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 如何使用Thread 启动 Callable
         * Thread的构造函数只能传Runnable，Runnable有个子类Futuretask类可以传入Callable
         *      new Thread(new Runnable()).start();
         *      new Thread(new futureTask<V>()).start();
         *      new Thread(new futureTask<>(callable)).start();
         */
        MyThread callable = new MyThread();
        FutureTask<String> futureTask = new FutureTask<>(callable);
        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start();
        String ret = futureTask.get();
        System.out.println(ret);
    }
}

/**
 * Callable<String> 泛型，代表的是Callable的返回值类型
 */
class MyThread implements Callable<String>{
    @Override
    public String call() throws Exception {
        System.out.println("Study callable："+Thread.currentThread().getName());
        return "lixueqiang";
    }
}
