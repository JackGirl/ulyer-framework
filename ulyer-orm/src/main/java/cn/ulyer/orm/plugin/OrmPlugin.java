package cn.ulyer.orm.plugin;

import cn.ulyer.orm.enums.PluginType;
import cn.ulyer.orm.mapper.MapperMethod;

import java.sql.PreparedStatement;
import java.util.Map;

public interface OrmPlugin {
    PluginType getType();

    Object plugin(PreparedStatement statement, MapperMethod mapperMethod, Map<String,Object> params);

}
