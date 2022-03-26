package cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class CASDemo02 {
    static AtomicStampedReference atomicStampedReference = new AtomicStampedReference<Integer>(1,1);
    public static void main(String[] args) {
        new Thread(() ->{
            System.out.println("a==>"+atomicStampedReference.getStamp());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicStampedReference.compareAndSet(1, 2,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println("a==>"+atomicStampedReference.getStamp());
            System.out.println(atomicStampedReference.compareAndSet(2, 1,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
        },"a").start();

        new Thread(() ->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println("b==>"+stamp);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //由于stamp不是，期望的stamp,所以会修改失败
            System.out.println(atomicStampedReference.compareAndSet(1, 2,
                    stamp, atomicStampedReference.getStamp() + 1));
        },"b").start();
    }
}
