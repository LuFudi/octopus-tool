package com.octopus.service.impl;

import com.octopus.entity.Company;
import com.octopus.mapper.CompanyMapper;
import com.octopus.service.ICompanyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fd
 * @since 2023-09-20
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements ICompanyService {


    public List<String> listCompany(Company company, String name,int page){

        return null;

    }
}
