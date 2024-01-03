package default方法Abstract类与函数式编程_2;

import java.util.stream.Stream;

/**
 * @author lfd
 */
public class FunctionalService {

    static String startUpOutPut(int speed, String startLocation){
        return  "起点是" + startLocation + ",速度是" + speed;
    }

    static <T>Stream<T> apply(T t){
        return (Stream<T>) t;
    }

}
