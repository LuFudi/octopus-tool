package generic泛型_1;

import lombok.Data;

/**
 * @author lfd
 */
@Data
public class Erasure <T>{
//public class Erasure <T extends  String>{

    private T value;

    public Erasure(T value) {
        this.value = value;
    }

    public void add(T value){

    }
}
