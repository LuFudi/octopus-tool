package com.octopus.aspect;

import com.octopus.entity.Company;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Administrator
 */
@Aspect
@Component
@Slf4j
public class CompanyAspect {

    /**
     * 精确到方法名
     */
    @Pointcut(value = "execution(* com.octopus.service.impl.CompanyServiceImpl.listCompany(..))")
    public void companyServiceImplListCompany(){}

    /**
     * 精确到类名
     */
    @Pointcut(value = "execution(* com.octopus.service.impl.CompanyServiceImpl.*(..))")
    public void companyServiceImpl(){}

    /**
     * 精确到方法，且可以更方便的获取到原方法的复杂入参
     * @param company
     * @param name
     * @param page
     */
    @Pointcut("execution(public java.util.List<String> listCompany(com.octopus.entity.Company,String,int)) && args(company, name,page)")
    public void putAsyncPointcut(Company company, String name,int page) {}

    @After("putAsyncPointcut(company,name, page)")
    //@After("companyServiceImpl()")
    public void companyPoint(JoinPoint joinPoint,Company company, String name,int page){
        System.out.println(company+name+page);
        log.info("AOP生效，方法名:"+joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        for(Object arg: args){
            if (arg instanceof String) {
                System.out.println(arg.toString());
            }

            if (arg instanceof Company) {

//                Company company = (Company) arg;
//                String name = company.getName();
                System.out.println("Company Object Name: " + name);
            }
        }

    }




}
