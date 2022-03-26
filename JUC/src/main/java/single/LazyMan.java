package single;

//普通的懒汉式单例模式
public class LazyMan {
    private LazyMan(){
        System.out.println(Thread.currentThread().getName());
    }
    private static LazyMan lazyMan;

    public static LazyMan getInstance(){
        if(lazyMan == null){
            lazyMan = new LazyMan();
        }
        return lazyMan;
    }

    public static void main(String[] args) {
      /*  System.out.println(LazyMan.getInstance()); single.LazyMan@12a3a380
          System.out.println(LazyMan.getInstance()); single.LazyMan@12a3a380*/

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(LazyMan.getInstance());
            }).start();
        }
        /**
         * single.LazyMan@49b7d06d
         * single.LazyMan@384e8869
         * single.LazyMan@378ea21c
         * single.LazyMan@378ea21c
         * single.LazyMan@22ff7883
         * single.LazyMan@7f608cfa
         * single.LazyMan@7f608cfa
         */
    }
}
