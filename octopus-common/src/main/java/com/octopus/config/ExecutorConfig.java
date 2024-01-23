package com.octopus.config;

import cn.hutool.core.thread.BlockPolicy;
import cn.hutool.core.thread.RejectPolicy;
import cn.hutool.core.thread.ThreadFactoryBuilder;
import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @author Administrator
 */
@Slf4j
@Configuration
public class ExecutorConfig {

    public final String EXCEL_EXECUTOR = "EXCEL_EXECUTOR";

    /**
     * 线程池
     *
     * @return
     */
    @Bean(name = "excelExecutor")
    public ExecutorService excelExecutor() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setDaemon(true)
                .setNamePrefix(EXCEL_EXECUTOR.concat("_")).build();

        return new ThreadPoolExecutor(4, 16,
                5, TimeUnit.SECONDS, new ArrayBlockingQueue(200), threadFactory, (r, executor) -> {
                    try {
                        log.info("Thread Pool is full");
                        //如果队列已满，则阻塞线程，直到有空间可用。确保任务一定执行
                        //批量任务可如此设置。若线程池的作用是并行加载资源(如页面数据)，则应采用直接拒绝的方式，否则会导致响应时间过长
                        executor.getQueue().put(r);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
