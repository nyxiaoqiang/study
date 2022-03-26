package lock8;


import java.util.concurrent.TimeUnit;

/**
 * 第一个线程调用的发短信方法睡了 4s,问输出的结果？ 发短信  打电话
 */
public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        Phone1 phone = new Phone1();
        new Thread(() ->{
            phone.sendSms();
        }).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() ->{
            phone.call();
        }).start();
    }
}

/**
 * 下面两个方法是非静态方法，所以synchronized锁的对象是方法的调用者（phone对象）
 * 由于两个方法用的是同一个锁（同一个对象调用），所以谁先拿到锁谁先执行
 */
class Phone1{
    public synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public synchronized void call(){
        System.out.println("打电话");
    }
}

