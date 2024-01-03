package generic泛型_1;

import lombok.Data;

/**
 * @author lfd
 * 方法名后面的<T,R> 代表两个不同的参数类型
 *
 */
@Data
public class ResultType<T,R> {

    private Integer code;

    private R message;

    private T data;


    /**
     * getMessages方法的返回值<S>S ,其中<S>代表返回值是泛型(可接受类型传入),S代表返回值的具体类型,
     *  *  且！S必须为ResultType这个类中已定义的泛型的一种（<>中的T,R,S都可算是一个类型）
     * @return
     * @param <S>
     */
    public <S>S getMessages(){
        return (S) message;
    }

    /**
     * <V> 仅代表返回值是泛型，并不代表具体类型。R才是具体类型
     * 为了避免混淆，如果在一个泛型类中存在泛型方法，那么两者的类型参数最好不要同名(除非是确定要使用类的已知类型，但是这样的话方法上的<V>实际上可以去掉)
     * @return
     * @param <V>
     */
    public <V>R getDatas(){
        return message;
    }
}
