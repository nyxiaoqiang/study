package testfunction;
//Supplier 供给型接口 没有参数，只有返回值
import java.util.function.Supplier;

public class SupplierTest {
    public static void main(String[] args) {
/*        Supplier<Integer> supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return 1024;
            }
        };*/
        Supplier<Integer> supplier = () -> {
            return 1024;
        };
        System.out.println(supplier.get());
    }
}
