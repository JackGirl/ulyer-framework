package com.test.mybatis.plugin;

import com.test.mybatis.enums.PluginType;
import com.test.mybatis.mapper.MapperMethod;

import java.sql.PreparedStatement;
import java.util.Map;

public interface OrmPlugin {
    PluginType getType();

    Object plugin(PreparedStatement statement, MapperMethod mapperMethod, Map<String,Object> params);

}
