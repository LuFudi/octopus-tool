package com.octopus.service.impl;

import com.octopus.entity.Region;
import com.octopus.mapper.RegionMapper;
import com.octopus.service.IRegionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fd
 * @since 2023-09-20
 */
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements IRegionService {

}
