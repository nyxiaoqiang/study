package single;

//饿汉式单例模式
public class Hungry {
    //1、私有化构造方法
    private Hungry(){

    }
    //2、私有化变量
    private final static Hungry HUNGRY = new Hungry();
    //3、提高共有的方法
    public static Hungry getInstance(){
        return HUNGRY;
    }
    public static void main(String[] args) {
        System.out.println(Hungry.getInstance());//single.Hungry@12a3a380
        System.out.println(Hungry.getInstance());//single.Hungry@12a3a380
    }
}