package com.octopus.controller;

import com.alibaba.fastjson2.JSONObject;
import com.octopus.base.Result;
import com.octopus.base.es.EsDataOperation;
import com.octopus.service.impl.CompanyServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fd
 * @since 2023-09-20
 */
@Slf4j
@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyServiceImpl companyService;


    @Autowired
    private EsDataOperation esDataOperation;

    @RequestMapping("/test")
    @ResponseBody
    public Result<String> test(){
        return Result.successWithData("正常返回");

    }


    @RequestMapping("/put")
    @ResponseBody
    public void put(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",1);
        map.put("amount",new BigDecimal("123000000.56"));
        esDataOperation.insert("test_amount",map);
    }


    @RequestMapping("/get")
    @ResponseBody
    public void get(){

        Map<String, Object> test_amount = esDataOperation.get("test_amount", "1");
        log.info("result:{}",test_amount);
        for (Map.Entry<String, Object> entry : test_amount.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof Double) {
                Double doubleValue = (Double) value;
                BigDecimal bigDecimalValue = BigDecimal.valueOf(doubleValue);
                String formattedValue = bigDecimalValue.toPlainString();
                test_amount.put(entry.getKey(), formattedValue);
            }
        }
        log.info("result:{}",test_amount);
    }
}
