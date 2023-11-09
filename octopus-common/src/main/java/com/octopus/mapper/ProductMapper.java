package com.octopus.mapper;

import com.octopus.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fd
 * @since 2023-09-20
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

}
