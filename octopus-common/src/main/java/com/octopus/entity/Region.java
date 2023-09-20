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
@TableName("tfd_region")
public class Region extends BaseEntity {

    @TableField("region_code")
    private String regionCode;

    @TableField("parent_code")
    private String parentCode;

    @TableField("`level`")
    private Integer level;

    @TableField("region_name")
    private String regionName;

    @TableField("latitude")
    private String latitude;

    @TableField("longitude")
    private String longitude;
}
