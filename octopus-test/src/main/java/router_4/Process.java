package router_4;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lenovo
 */
@Component
public  abstract class Process<T> implements InitializingBean {
    Map<OperatorEnum,Process> processMap=new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        processMap.put(getOperator(), this);
    }
    public abstract OperatorEnum getOperator();

    public abstract void excute(T t);

    public void newTest() {

    }

    public Process getProcess(OperatorEnum operatorType){
        return processMap.get(operatorType);
    }


}
