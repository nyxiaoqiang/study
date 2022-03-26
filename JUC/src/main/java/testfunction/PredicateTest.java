package testfunction;

import java.util.function.Predicate;
//断定型接口：有一个输入参数，返回值只能是 布尔值！
public class PredicateTest {
    public static void main(String[] args) {
        /*Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String o) {
                return o.isEmpty();
            }
        };*/
        Predicate<String> predicate = str -> {
            return str.isEmpty();
        };
        System.out.println(predicate.test("李雪强"));
    }
}
