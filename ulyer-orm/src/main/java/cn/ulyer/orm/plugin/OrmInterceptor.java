package cn.ulyer.orm.plugin;

import cn.ulyer.orm.enums.PluginType;
import cn.ulyer.orm.mapper.MapperMethod;

import java.sql.PreparedStatement;
import java.util.Map;

public interface OrmInterceptor {

    PluginType getType();

    Object plugin(Invocation invocation);

}