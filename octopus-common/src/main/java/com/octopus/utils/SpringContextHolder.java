package com.octopus.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextHolder implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//该方法可获取到applicationContext
        SpringContextHolder.applicationContext = applicationContext;
    }
    public static <T> T getBean(String beanName) {
        assertApplicationContext();
        return (T)applicationContext.getBean(beanName);
    }
    public static <T> T getBean(Class<T> requiredType) {
        assertApplicationContext();
        return applicationContext.getBean(requiredType);
    }
    public static ApplicationContext getApplicationContext(){
        assertApplicationContext();
        return applicationContext;
    }
    private static void assertApplicationContext(){
        if (applicationContext == null) {
            throw new RuntimeException("applicationContext属性为null,请检查是否注入SpringContextHolder");
        }
    }
}
