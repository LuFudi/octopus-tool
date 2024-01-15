package com.octopus.service.impl;

import com.octopus.entity.ShelfModelSnapShot;
import com.octopus.mapper.ShelfModelSnapShotMapper;
import com.octopus.service.IShelfModelSnapShotService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 型号库存快照 服务实现类
 * </p>
 *
 * @author fd
 * @since 2024-01-12
 */
@Service
public class ShelfModelSnapShotServiceImpl extends ServiceImpl<ShelfModelSnapShotMapper, ShelfModelSnapShot> implements IShelfModelSnapShotService {
    @Autowired
    ShelfModelSnapShotMapper shelfModelSnapShotMapper;


    public void imported(){

    }

}
