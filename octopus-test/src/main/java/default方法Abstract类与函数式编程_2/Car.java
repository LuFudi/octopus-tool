package default方法Abstract类与函数式编程_2;

/**
 * @author lfd
 */
@FunctionalInterface
public interface Car {
    /**
     * 接口也可以设置变量，但这个变量默认是被static final修饰的
     */
    static final int defaultHeight = 10;

    /**
     * 车子启动
     * 实际上,接口中的方法的默认修饰符都是public abstract,即，接口中的每个方法都是抽象方法（因为接口的每个实现类都需要实现他的普通方法）
     * 接口中有且只有一个抽象方法，这个就叫函数式编程，可配合Lambda表达式使用
     * @FunctionalInterface 注解用于检验是否符合函数式编程的定义
     * @return
     */
    abstract String startUp(int speed, String startLocation);

    /**
     * 获取车子长度
     * java8 新增了default方法，
     * 目的是在接口中可以新增方法而不需要改动其实现类（实现类可以不必重写default方法而是直接使用）
     * default方法与 static方法的区别在于：default方法必须由实现类的实例来调用(taxi实现了car，调用该方法就是 new Taxi().getLength)
     * static可以由类来调用 (Car.getVolume)
     * @return
     */
    default String getLength() {
        return "车子的长度是3米5";
    }


    static Integer getVolume() {
        return 1;
    }




}
