package pc;

/**
 * 真正的多线程开发，公司中的开发，需要降低耦合度。
 * 线程就是一个单独的资源类，没有任何的附属操作
 * 例如下面的Ticket
 */
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
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
class Ticket{
    //属性、方法
    private int number = 30;

    //买票的方法
    //为了避免重复卖票，加锁
    public synchronized void sale(){
        if(number > 0){
            System.out.println(Thread.currentThread().getName()+"卖了"+number--+"张票");
        }
    }

}