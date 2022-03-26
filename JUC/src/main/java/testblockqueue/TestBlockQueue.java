package testblockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 练习队列的四组api
 */
public class TestBlockQueue {
    public static void main(String[] args) throws InterruptedException {
        test4();
    }

    // add和remove有返回值，会抛出异常
    private static void test1(){
        //3代表的是队列大小，超过则抛出异常
        ArrayBlockingQueue queue = new ArrayBlockingQueue(3);
        System.out.println(queue.add("a"));
        System.out.println(queue.add("b"));
        System.out.println(queue.add("c"));
        //java.lang.IllegalStateException: Queue full 抛出异常
        //System.out.println(queue.add("d"));
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        //java.util.NoSuchElementException  抛出异常
        //System.out.println(queue.remove());
    }

    //offer poll有返回值，不会抛出异常
    private static void test2(){
        //3代表的是队列大小，超过则返回false
        ArrayBlockingQueue queue = new ArrayBlockingQueue(3);
        System.out.println(queue.offer("a"));
        System.out.println(queue.offer("b"));
        System.out.println(queue.offer("c"));
        //System.out.println(queue.offer("d"));   //false

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        //System.out.println(queue.poll());   //null
    }

    //put take 等待阻塞，一直阻塞
    private static void test3() throws InterruptedException {
        //3代表的是队列大小，超过则阻塞等待
        ArrayBlockingQueue queue = new ArrayBlockingQueue(3);
        queue.put("a");
        queue.put("b");
        queue.put("c");
        //queue.put("d");//超过队列大小，会一直等待，死等
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
        //System.out.println(queue.take());//没有这个元素，会一直阻塞
    }

    //put take 等待阻塞，超时等待
    private static void test4() throws InterruptedException {
        //3代表的是队列大小，超过则阻塞等待
        ArrayBlockingQueue queue = new ArrayBlockingQueue(3);
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        queue.offer("d",2, TimeUnit.SECONDS);//超过队列大小，会一直等待，死等
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll(2,TimeUnit.SECONDS));
    }
}
