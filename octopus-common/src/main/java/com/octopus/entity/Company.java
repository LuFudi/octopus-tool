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
@TableName("tfd_company")
public class Company extends BaseEntity {

    @TableField("`name`")
    private String name;
}
