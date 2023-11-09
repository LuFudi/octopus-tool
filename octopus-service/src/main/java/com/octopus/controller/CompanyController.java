package com.octopus.controller;

import com.octopus.entity.Company;
import com.octopus.service.impl.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fd
 * @since 2023-09-20
 */
@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyServiceImpl companyService;

    @RequestMapping("/test")
    @ResponseBody
    public void test(){
        Company company = new Company();
        company.setName("张三");
        company.setId(11);
        List<String> list = companyService.listCompany(company, "新name", 50);

    }

}
