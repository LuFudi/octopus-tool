package com.octopus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.octopus.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author fd
 * @since 2023-09-20
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tfd_product")
public class Product extends BaseEntity {

    @TableField("product_code")
    private String productCode;

    /**
     * 币种
     */
    @TableField("currency")
    private String currency;

    /**
     * 产品规格
     */
    @TableField("package_quantity")
    private Integer packageQuantity;

    @TableField("product_name")
    private String productName;

    @TableField("price")
    private Long price;

    /**
     * 不含税单价
     */
    @TableField("unit_price_excluding_tax")
    private Long unitPriceExcludingTax;
}
