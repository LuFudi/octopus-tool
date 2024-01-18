package router_4;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author Lenovo
 */
public class OrderProcess<T> extends Process {

    @Override
    public OperatorEnum getOperator() {
        return OperatorEnum.OPERATOR_ORDER;
    }



    @Override
    public void excute(Object req) {


        Map<String,Object>  map = JSONObject.parseObject(JSON.toJSONString(req));

        //不同模块的执行逻辑
    }




}
