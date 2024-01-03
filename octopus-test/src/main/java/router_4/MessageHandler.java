package router_4;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 */
public class MessageHandler {
    @Autowired
    private Process process;
    private void handler(JSONObject message) throws Exception {
        OperatorEnum operatorEnum =(OperatorEnum) message.get("Operatortype");
        Map<String ,String> map = new HashMap<>();
        List<String> list = new ArrayList<>();
         process.getProcess(operatorEnum).excute(map);
         List list1 = new ArrayList();


    }
}
