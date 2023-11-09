package com.octopus.generator;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.*;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import com.octopus.base.BaseEntity;

import java.util.Collections;

// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class MybatisGenerator {
    public static void main(String[] args) {

        //连接配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder("jdbc:mysql://127.0.0.1:3306/world", "root", "123456")
                .schema("mybatis-plus")
                .keyWordsHandler(new MySqlKeyWordsHandler())
                .build();
        AutoGenerator autoGenerator = new AutoGenerator(dataSourceConfig);
        //基础配置
        GlobalConfig globalconfig = new GlobalConfig.Builder()
                //代码生成的基础位置
                .outputDir("D:/Git Repostory/study/octopus-tool/octopus-common/src/main/java/")
                .author("fd")
                .dateType(DateType.TIME_PACK)
                .commentDate("yyyy-MM-dd")
                .build();
        //每种文件类型的包配置
        PackageConfig packageConfig = new PackageConfig.Builder()
                //xml文件单独配置位置
                .pathInfo(Collections.singletonMap(OutputFile.xml, "D:/Git Repostory/study/octopus-tool/octopus-common/src/main/resources/xml"))
                //父包名
                .parent("com.octopus")
                .entity("entity")
                .service("service")
                .serviceImpl("service.impl")
                .mapper("mapper")
                .controller("controller")
                .build();
        //表配置
        StrategyConfig tableConfig = new StrategyConfig.Builder()
                //模糊匹配表名，与notLikeTable只能配置一个
                .likeTable(new LikeTable("tfd"))
                //.notLikeTable(LikeTable)
                //统一去除前缀
                .addTablePrefix("tfd_","Tfd_")
                //后缀匹配
                //.addFieldSuffix("_flag")
                .build()
                .entityBuilder()
                //设置父类继承
                .superClass(BaseEntity.class)
                //禁用生成SerialVersionUID
                .disableSerialVersionUID()
                //自动覆盖已生成文件
                .enableFileOverride()
                //开启链式模型
                .enableChainModel()
                //启用lombok
                .enableLombok()
                //开启 Boolean 类型字段移除 is 前缀
                .enableRemoveIsPrefix()
                //生成实体时生成字段注解
                .enableTableFieldAnnotation()
                //乐观锁字段名(数据库字段)
                .versionColumnName("version")
                //逻辑删除字段名(数据库字段)
                .logicDeleteColumnName("is_deleted")
                //数据库表映射到实体的命名策略---下划线转驼峰
                .naming(NamingStrategy.no_change)
                //父类公共字段
                .addSuperEntityColumns("id","is_deleted", "created_by", "created_time", "updated_by", "updated_time")
                //下划线转驼峰
                .naming(NamingStrategy.underline_to_camel)
                //忽略字段
                //.addIgnoreColumns("age")
                //文件名称转换
                //.convertFileName(new CoverFileName())
                //字段填充方式 --分为插入时填充，修改时填充，插入修改时填充。其填充方式类似于审计字段的生成-->https://baomidou.com/pages/4c6bcf/
                //.addTableFills(new Column("create_time", FieldFill.INSERT))
                //.addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE))
                //格式化文件名称
                //.formatFileName("%sEntity")
                .mapperBuilder()
                .mapperAnnotation(org.apache.ibatis.annotations.Mapper.class)
                .build();

        autoGenerator.global(globalconfig)
                .packageInfo(packageConfig)
                .strategy(tableConfig)
                //.strategy(entityConfig)
                .execute();
    }


}


