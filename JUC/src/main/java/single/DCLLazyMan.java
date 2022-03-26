package single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

//DCL懒汉式单例模式
public class DCLLazyMan {

    private DCLLazyMan(){
        System.out.println(Thread.currentThread().getName());
    }

    private volatile static DCLLazyMan lazyMan;

    //双重检测锁模式的 懒汉式单例 DCL懒汉式
    public static DCLLazyMan getInstance(){
        if(lazyMan == null){
           synchronized (DCLLazyMan.class){
               if(lazyMan == null){
                   ////不是一个原子性操作，可能会存在指令重排
                   lazyMan = new DCLLazyMan();
               }
           }
        }
        return lazyMan;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(DCLLazyMan.getInstance());
            }).start();
        }
        /**
         * single.DCLLazyMan@1025efd2
         * single.DCLLazyMan@1025efd2
         * single.DCLLazyMan@1025efd2
         * .......
         */
        //使用反射破坏DCL模式
        Constructor<DCLLazyMan> declaredConstructor = DCLLazyMan.class.getDeclaredConstructor(null);
        //破坏构造函数私有化
        declaredConstructor.setAccessible(true);
        DCLLazyMan dclLazyMan1 = declaredConstructor.newInstance();
        DCLLazyMan dclLazyMan2 = declaredConstructor.newInstance();
        System.out.println(dclLazyMan1);//single.DCLLazyMan@12a3a380
        System.out.println(dclLazyMan2);//single.DCLLazyMan@12a3a380
    }
}
