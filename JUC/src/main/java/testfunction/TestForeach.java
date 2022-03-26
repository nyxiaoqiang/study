package testfunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.DoubleStream;
import java.util.stream.LongStream;

public class TestForeach {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("first");
        list.add("second");
        list.add("third");
        list.add("four");
        list.add("five");
        Consumer consumer = str -> { System.out.println(str); };
        list.forEach(consumer);
        System.out.println("--------------------");
        list.forEach( str -> System.out.println(str));
        System.out.println("--------------------");
        list.forEach(System.out::println);
        System.out.println("--------------------");
        //自定义消费行为动作实例
        list.forEach(new MyConsumer());
        System.out.println("----------map遍历----------");
        //map.forEach(BiConsumer)  accept(T t, U u);
        Map<String,String> map = new HashMap<>();
        map.put("A","1");
        map.put("B","2");
        map.put("C","3");
        map.forEach((k,v) -> System.out.println(k+"---"+v));

    }
}
class MyConsumer implements Consumer<Object>{

    @Override
    public void accept(Object t) {
        System.out.println("打印输出(动作执行的参数)：" + t);
    }
}


