package com.octopus.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.octopus.entity.ShelfModelSnapShot;
import com.octopus.excel.dto.ShelfModelSnapShotDto;
import com.octopus.service.impl.ShelfModelSnapShotServiceImpl;
import com.octopus.utils.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * @author Administrator
 */
@Slf4j
public class ShelfModelListener implements ReadListener<ShelfModelSnapShotDto> {

    ExecutorService excelExecutor;

    ShelfModelSnapShotServiceImpl shelfModelSnapShotService;
    /**
     * 单次处理数据
     */
    private final int BATCH_COUNT = 1000;

    /**
     * 统计导入进度
     */
    private int num = 0;

    private boolean isFirstData = true;

    /**
     * 便于统计耗费时间
     */
    private CountDownLatch countDownLatch;
    /**
     * 每1000条数据执行开启一个线程执行一次插入
     */
    List<ShelfModelSnapShot> tmpList = new ArrayList<>(BATCH_COUNT);


    /**
     * 方法开始时间
     */
    long startTime;

    public  ShelfModelListener(){
        this.excelExecutor = SpringContextHolder.getBean("excelExecutor");
        this.shelfModelSnapShotService = SpringContextHolder.getBean(ShelfModelSnapShotServiceImpl.class);

    }


    @Override
    public void invoke(ShelfModelSnapShotDto shelfModelSnapShot, AnalysisContext analysisContext) {
        if (isFirstData) {
            startTime = System.currentTimeMillis();
            //Integer rowNumber = analysisContext.readSheetHolder().getApproximateTotalRowNumber();
            Integer rowNumber =1268029;
            isFirstData = false;
            int count = rowNumber / 500;
            countDownLatch = new CountDownLatch(count + 1);
        }
        ///shelfModelSnapShot.init(1, LocalDateTime.now());
        ShelfModelSnapShot shelfModelSnapShotEntity = new ShelfModelSnapShot();
        BeanUtils.copyProperties(shelfModelSnapShot,shelfModelSnapShotEntity);
        tmpList.add(shelfModelSnapShotEntity);
        if (tmpList.size() < BATCH_COUNT) {
            return;
        }
        num++;
        List<ShelfModelSnapShot> importList = new ArrayList<>(tmpList);
        tmpList.clear();
        excelExecutor.submit(() -> {
            try {
                this.processData(importList);
            } catch (Exception e) {
                log.info("===========批量插入异常,exception:", e);
            } finally {
                countDownLatch.countDown();
                log.info("第{}批次导入结束！", num);
                importList.clear();
            }
        });
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        this.processData(tmpList);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            long endTime = System.currentTimeMillis();
            long cost = endTime - startTime;
            log.info("========本方法执行完毕，总耗时：{}秒==================",cost/1000);
        }

    }

    public void processData(List<ShelfModelSnapShot> importList) {
        long startTime = System.currentTimeMillis();

        shelfModelSnapShotService.saveBatch(importList);

        // 方法 a 的业务逻辑
        long endTime = System.currentTimeMillis();
        long cost = endTime - startTime;
        log.info("单次插入耗时:{}毫秒",cost);
    }
}
