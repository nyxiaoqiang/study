package testfunction;

import java.util.function.Consumer;
// Consumer 消费型接口: 只有输入，没有返回值
public class ConsumerTest {
    public static void main(String[] args) {
/*        Consumer<String> stringConsumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s+" study function");
            }
        };*/
        Consumer<String> stringConsumer = (str) -> System.out.println(str+" study function");
        stringConsumer.accept("李雪强");
    }
}
