package com.octopus.service.impl;

import com.octopus.entity.Product;
import com.octopus.mapper.ProductMapper;
import com.octopus.service.IProductService;
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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
