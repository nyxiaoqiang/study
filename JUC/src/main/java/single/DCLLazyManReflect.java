package single;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

//DCL懒汉式单例模式
public class DCLLazyManReflect {
    //使用指示灯来避免反射破坏单例模式
    private static boolean index = false;
    private DCLLazyManReflect(){
        synchronized (DCLLazyManReflect.class) {
            if(!index){
                index = true;
            }else {
                throw new RuntimeException("不要试图使用反射破坏单例异常");
            }
        }
    }

    private volatile static DCLLazyManReflect lazyMan;

    //双重检测锁模式的 懒汉式单例 DCL懒汉式
    public static DCLLazyManReflect getInstance(){
        if(lazyMan == null){
           synchronized (DCLLazyManReflect.class){
               if(lazyMan == null){
                   ////不是一个原子性操作，可能会存在指令重排
                   lazyMan = new DCLLazyManReflect();
               }
           }
        }
        return lazyMan;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        /*for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(DCLLazyManReflect.getInstance());
            }).start();
        }*/
        /**
         * single.DCLLazyMan@1025efd2
         * single.DCLLazyMan@1025efd2
         * single.DCLLazyMan@1025efd2
         * .......
         */
        //使用反射破坏DCL模式(可以使用红绿灯避免这种破坏)
        Constructor<DCLLazyManReflect> declaredConstructor = DCLLazyManReflect.class.getDeclaredConstructor(null);
        //破坏构造函数私有化
        declaredConstructor.setAccessible(true);
        /*DCLLazyManReflect dclLazyMan1 = declaredConstructor.newInstance();
        DCLLazyManReflect dclLazyMan2 = declaredConstructor.newInstance();
        System.out.println(dclLazyMan1);//single.DCLLazyMan@12a3a380
        System.out.println(dclLazyMan2);//single.DCLLazyMan@12a3a380*/

        //破坏红绿灯单例模式
        Class<DCLLazyManReflect> dclLazyManReflectClass = DCLLazyManReflect.class;
        Field index = dclLazyManReflectClass.getDeclaredField("index");
        Constructor<DCLLazyManReflect> declaredConstructor1 = dclLazyManReflectClass.getDeclaredConstructor(null);
        DCLLazyManReflect dclLazyManReflect1 = declaredConstructor1.newInstance();
        index.setBoolean(declaredConstructor1,false);
        DCLLazyManReflect dclLazyManReflect2 = declaredConstructor1.newInstance();
        System.out.println(dclLazyManReflect1);
        System.out.println(dclLazyManReflect2);


    }
}
