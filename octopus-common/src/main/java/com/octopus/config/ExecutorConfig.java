package com.octopus.config;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @author Administrator
 */
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
        return new ThreadPoolExecutor(5, 20,
                5, TimeUnit.SECONDS, new ArrayBlockingQueue(2000), threadFactory);
    }
}
