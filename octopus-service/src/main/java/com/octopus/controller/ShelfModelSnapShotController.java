package com.octopus.controller;

import com.alibaba.excel.EasyExcel;
import com.octopus.excel.ShelfModelListener;
import com.octopus.excel.dto.ShelfModelSnapShotDto;
import com.octopus.service.impl.ShelfModelSnapShotServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 * 型号库存快照 前端控制器
 * </p>
 *
 * @author fd
 * @since 2024-01-12
 */
@Controller
@RequestMapping("/shelfModelSnapShot")
@Slf4j
public class ShelfModelSnapShotController {

    @Autowired
    ShelfModelSnapShotServiceImpl shelfModelSnapShotService;


    @RequestMapping("/import")
    @ResponseBody
    public void imported() throws FileNotFoundException {
        log.info("导入开始");
        String filePath = "D://outPut/sas_ivt_shelf_model_snap_shot.csv";
        //String filePath = "D://outPut/test.csv";
        ShelfModelListener shelfModelListener= new ShelfModelListener();
        EasyExcel.read(filePath, ShelfModelSnapShotDto.class, shelfModelListener)
                .charset(StandardCharsets.UTF_8)
                .sheet().doRead();
    }

}
