package com.octopus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Administrator
 */
@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan("com.octopus.mapper")
public class OctopusApplication {
    public static void main(String[] args) {
        SpringApplication.run(OctopusApplication.class, args);
    }
}
