package default方法Abstract类与函数式编程_2;


import entity_0.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static default方法Abstract类与函数式编程_2.Bus.startVehicle;

/**
 * @author lfd
 */
public class Test {

    public static void main(String[] args) {

        //推导函数式接口的应用
        /** 第一步、
         * 首先，startVehicle有三个参数,第一个参数为函数式接口
         * 实际上，函数式接口的第一步依然是接口实例化，也就是通过匿名内部类实现接口方法
         */
        String result = startVehicle(new Car() {
            @Override
            public String startUp(int speed, String startLocation) {
                return  "起点是" + startLocation + ",速度是" + speed;
            }
        }, 10, "上海");

        /**
         * 第二步：
         * 匿名内部类用lambda表达式简化，但是这里有一个前提要求，就是接口中必须只有一个抽
         * 象方法。原因是lambda表达式中没有方法名，如果接口中有多个抽象方法，lambda表达式
         * 就无法将其简化（因为不知道该将哪个方法转化）
         */
        String result1 = startVehicle(((speed, startLocation) -> {
            return "起点是" + startLocation + ",速度是" + speed;
        }), 10, "上海");


        /**进阶
         * FunctionalTest中的这个方法使用了和car.startUp方法相同的入参和返回值,实际上就可以直接替换后者（前提是入参和返回值相同）
         */
        String result2 = startVehicle(FunctionalService::startUpOutPut, 10, "上海");


        System.out.println(result);
        System.out.println(result1);
        System.out.println(result2);

        List<Person> personList = new ArrayList<>();
        /**
         * 下面是一个经典的stream.map()函数式编程推导过程
         * stream.map()方法  <R> Stream<R> map(Function<? super T, ? extends R> mapper); en
         * 点进去看到 Function是一个典型的函数式接口,R apply(T t); 只有这一个抽象方法。
         *当我们调用map方法时，可以首先使用Function的匿名内部类,<Person,String>分别对应apply方法的入参和出参类型
         */
        personList.stream().map(new Function<Person,String>() {
            @Override
            public String apply(Person person){
                return person.getName();
            }
        });

        /**
         * 二、简化匿名内部类
         * (s1) 这里实际上代表的是Function中的apply方法，因此只需要一个参数
         */
        personList.stream().map(s1->{
            return s1.getName();
        });
        /**
         * 三、完全简化
         */
        List<Integer> personIds = personList.stream().map(Person::getId).collect(Collectors.toList());
    }
}
