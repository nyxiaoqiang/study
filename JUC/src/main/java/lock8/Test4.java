package lock8;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * 发短信和打电话两个方法改成静态方法，结果是什么
 */
public class Test4 {
    public static void main(String[] args) throws InterruptedException {
        Phone4 phone1 = new Phone4();
        Phone4 phone2 = new Phone4();
        new Thread(() ->{
            phone1.sendSms();
        }).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() ->{
            phone2.call();
        }).start();
    }
}

/**
 * 如果一个方法是静态方法，这个方法是早于对象出现的。
 * 类一加载就有了静态方法，所以静态同步方法锁的是Class对象Phone3.class
 */
class Phone4{
    public static synchronized void sendSms(){
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