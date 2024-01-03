package default方法Abstract类与函数式编程_2;

/**
 * 客车
 * 抽象类可以实现接口但不必实现接口中的方法
 *因为抽象类本身被设计成只能用于被继承，因此，抽象类可以强迫子类实现其定义的抽象方法，否则编译会报错。因此，抽象方法实际上相当于定义了“规范”。
 * 比如：当前类设置了offTheCar方法，由于抽象类不能被实例化，只能其子类继承他，并实现这个抽象方法（不同子类可以设置不同逻辑，抽象类只起到了规范的左右）
 * @author dell
 */
public abstract class AbstractCar implements Car{

    /**
     * 抽象类中的抽象方法可以没有返回体
     * 乘客下车
     * @return
     */
    abstract String offTheCar(int customerId);

    /**
     * 让乘客上车
     * 抽象类中的普通方法与 普通类中的方法是一致的，必须有返回
     * @param customerId
     * @return
     */
    String onBoard(int customerId){
        return "乘客"+customerId+"号成功上车";
    }

}
