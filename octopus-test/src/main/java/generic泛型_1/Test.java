package generic泛型_1;

import entity_0.Person;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lfd
 */
public class Test {

    public static void main(String[] args) {
        ResultType<Person,Integer> resultType = new ResultType();
        resultType.setData(new Person("张三"));
        resultType.setMessage(1);
        System.out.println(resultType);
        Object message = resultType.getMessages();
        System.out.println( message);
        Integer message1 =  resultType.getDatas();
        System.out.println(message1);


        //通配符 只可以添加Integer的子类
        List<? extends Integer> list = new ArrayList<>();
        //无限定的通配符 提供了只读的功能，也就是它删减了增加具体类型元素的能力
        List<? > list1 = new ArrayList<>();
        //通配符 只可以添加Integer的父类
        List<? super Integer> list2 = new ArrayList<>();

        //类型擦除
        List<String> l1 = new ArrayList<String>();
        List<Integer> l2 = new ArrayList<Integer>();
        //true
        System.out.println(l1.getClass() == l2.getClass());

        /**
         * 通过反射测试泛型擦除
         * 泛型类被类型擦除的时候，之前泛型类中的类型参数部分如果没有指定上限，
         * 如 <T>则会被转译成普通的 Object 类型，如果指定了上限如 <T extends String>则类型参数就被替换成类型上限。
         *
         */
        Erasure<String> erasure = new Erasure<>("擦除");
        Class eclz = erasure.getClass();
        System.out.println("erasure class is:"+eclz.getName());
        //获取测试类中的字段，可以看到打印结果是Object类型（替换掉了我们的String泛型）
        Field[] fs = eclz.getDeclaredFields();
        for ( Field f:fs) {
            System.out.println("Field name "+f.getName()+" type:"+f.getType().getName());
        }


    }
}
