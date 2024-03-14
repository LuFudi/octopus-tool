package com.octopus.service.impl;

import com.octopus.entity.Company;
import com.octopus.mapper.CompanyMapper;
import com.octopus.service.ICompanyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.octopus.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
@Slf4j
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements ICompanyService {

    @Resource
    private RedisUtils redisUtils;

    public List<String> listCompany(){

        redisUtils.incr("加一",5);
        log.info("service方法已执行");
        return null;

    }
}
