package testfunction;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class Test {
    public static void main(String[] args) {
        //使用双冒号::来构造静态函数引用。
        Function<String, Integer> function = Integer::parseInt;
        Integer apply = function.apply("123");
        System.out.println(apply);

        //使用双冒号::来构造非静态函数引用
        String content = "Study Jdk1.8";
        Function<Integer, String> function1 = content::substring;
        System.out.println(function1.apply(2));

        //构造函数引用
        BiFunction<String,Integer,User1> biFunction = User1::new;
        User1 user1 = biFunction.apply("李雪强", 18);
        System.out.println(user1);

        // 函数引用也是一种函数式接口，所以也可以将函数引用作为方法的参数
        User u1 = new User(1,"a",21);
        User u2 = new User(2,"b",22);
        User u3 = new User(3,"c",23);
        List<User> users = Arrays.asList(u1, u2, u3);
        Consumer consumer = System.out::println;
        users.forEach(consumer);
    }
}

@Data
class User1{
    private String userName;
    private Integer age;

    public User1(String userName, Integer age) {
        this.userName = userName;
        this.age =age;
    }
}
