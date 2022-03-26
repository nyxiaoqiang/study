package single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum EnumSingle {

    INSTANCE;

    public EnumSingle getInstance(){
        return INSTANCE;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
/*        EnumSingle instance = EnumSingle.INSTANCE;
        EnumSingle instance2 = EnumSingle.INSTANCE;
        System.out.println(instance);
        System.out.println(instance2);*/
        /* EnumSingle instance1 = EnumSingle.INSTANCE;
        //Exception in thread "main" java.lang.NoSuchMethodException
        Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        EnumSingle instance2 = declaredConstructor.newInstance();
        System.out.println(instance1);
        System.out.println(instance2)*/;
        Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(String.class, int.class);
        declaredConstructor.setAccessible(true);
        //IllegalArgumentException: Cannot reflectively create enum objects
        EnumSingle instance1 = declaredConstructor.newInstance();
        EnumSingle instance2 = EnumSingle.INSTANCE;
        System.out.println(instance1);
        System.out.println(instance2);
    }
}
