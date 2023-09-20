package com.octopus.generator;

import com.baomidou.mybatisplus.generator.function.ConverterFileName;

/**
 * 去除生成的实体类的前缀
 */
public class CoverFileName implements ConverterFileName {

    /**
     * 要去除的表的前缀(首字母必须大写)
     */

    final String prefix = "Tfd_";
    @Override
    public String convert(String entityName) {
        if(entityName.contains(prefix)){
            //去除前缀，且首字母变为大写
            String newTable = entityName.substring(prefix.length());
            newTable = newTable.substring(0, 1).toUpperCase() + newTable.substring(1);
            return newTable;
        }

        return entityName;
    }
}
