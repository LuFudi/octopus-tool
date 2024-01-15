package com.octopus.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

/**
 * @author lfd
 */
@Data
public abstract class BaseEntity<T extends BaseEntity> {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private Integer createdBy;


    private LocalDateTime createdTime;

    private Integer updatedBy;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updatedTime;

    /**
     * 逻辑删除
     */
    @TableLogic(value="0",delval="1")
    @TableField("IS_DELETED")
    private boolean deleted = false;

    private Integer version;

    /**
     * 创建某个对象后执行此方法，用来初使化创建人、创建时间、更新人、更新时间
     *
     * @param userId 创建人ID，初使修改人ID和创建人ID相同
     * @param localDateTime 创建时间，初使修改时间和创建时间相同
     */
    public BaseEntity<T> init(Integer userId, LocalDateTime localDateTime) {
        this.setCreatedTime(localDateTime);
        this.setCreatedBy(userId);
        this.setUpdatedTime(localDateTime);
        this.setUpdatedBy(userId);
        this.setVersion(1);
        return this;
    }

    /**
     * 创建某个对象后执行此方法，用来初使化创建人、创建时间、更新人、更新时间
     *
     * @param userId 创建人ID，初使修改人ID和创建人ID相同
     */
    public BaseEntity<T> init(Integer userId) {
        return init(userId, LocalDateTime.now());
    }

    /**
     * 当修改某个对象后执行此方法，用来修改更新人，更新时间，同时让版本号加1
     *
     * @param userId 更新人ID
     * @param localDateTime 更新时间
     */
    public BaseEntity<T> touch(Integer userId, LocalDateTime localDateTime) {
        this.setUpdatedTime(localDateTime);
        this.setUpdatedBy(userId);
        return this;
    }

    /**
     * 当修改某个对象后执行此方法，用来修改更新人，更新时间，同时让版本号加1
     *
     * @param userId 更新人ID
     */
    public BaseEntity<T> touch(Integer userId) {
        return touch(userId, LocalDateTime.now());
    }

    /**
     * 依赖是commons-lang
     * 继承BaseEntity的类也可以直接打印
     * @return
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }

}
