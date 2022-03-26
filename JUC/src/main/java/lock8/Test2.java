package lock8;
import java.util.concurrent.TimeUnit;
//两个对象，结果是什么
public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        Phone2 phone1 = new Phone2();
        Phone2 phone2 = new Phone2();
        new Thread(() ->{
            phone1.sendSms();
        }).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() ->{
            phone1.hello();
        }).start();
    }
}

/**
 * 下面两个方法是非静态方法，所以synchronized锁的对象是方法的调用者（phone对象）
 * 由于两个方法用的是同一个锁（同一个对象调用），所以谁先拿到锁谁先执行
 */
class Phone2{
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

    public void hello(){
        System.out.println("hello");
    }
}