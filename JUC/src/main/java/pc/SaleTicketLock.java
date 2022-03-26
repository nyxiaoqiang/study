package pc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 真正的多线程开发，公司中的开发，需要降低耦合度。
 * 线程就是一个单独的资源类，没有任何的附属操作
 * 例如下面的Ticket
 */
public class SaleTicketLock {
    public static void main(String[] args) {
        Ticket2 ticket = new Ticket2();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"A").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"B").start();
    }
}

//资源类 oop
//lock的三部曲：new ReentrantLock()，lock.lock();lock.unlock();
class Ticket2{
    //属性、方法
    private int number = 30;

    Lock lock = new ReentrantLock();

    //买票的方法
    //为了避免重复卖票，加锁
    public void sale(){
        lock.lock();

        try {
            //业务代码
            if(number > 0){
                System.out.println(Thread.currentThread().getName()+"卖了"+number--+"张票");
            }
        } finally {
            lock.unlock();//解锁
        }
    }
}