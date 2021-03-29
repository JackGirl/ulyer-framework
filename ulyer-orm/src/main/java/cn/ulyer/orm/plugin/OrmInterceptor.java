package cn.ulyer.orm.plugin;

import cn.ulyer.orm.enums.PluginType;

import java.lang.reflect.InvocationTargetException;

public interface OrmInterceptor {

    PluginType getType();

    Object plugin(Invocation invocation) throws InvocationTargetException, IllegalAccessException;

}
