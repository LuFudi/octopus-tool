package default方法Abstract类与函数式编程_2;

/**
 * @author lfd
 * 函数式编程的实际应用
 * 普通的类继承了抽象类之后，必须实现抽象类和他继承的接口中的抽象方法
 */
public class Bus extends AbstractCar implements Car{

    /**
     *
     * @param car 将函数式编程方法作为参数传入方法，可以通过lambda表达式重写方法的方式，修改方法的实现
     * @param speed
     * @param StartLocation
     * @return
     */
     static String startVehicle(Car car, int speed, String StartLocation){
         return car.startUp(speed,StartLocation);
     }



    @Override
    public String startUp(int speed, String StartLocation) {
        return "起点是"+StartLocation+",速度是"+speed;
    }

    @Override
    String offTheCar(int customerId) {
        return null;
    }

    /**
     * 抽象类中的普通方法，可以选择性的重写
     * @param customerId
     * @return
     */
    @Override
    String onBoard(int customerId){
        return super.onBoard(customerId);
    }

    /**
     * 接口中的default方法，可以选择性的重写
     * @return
     */
    @Override
    public String getLength() {
        return super.getLength();
    }


    /**
     * 静态方法与父类重名也可，互不影响
     * @return
     */
    Integer getVolume(){
        return 1;
    }

    static int te(){
        return  1;
    }
}
