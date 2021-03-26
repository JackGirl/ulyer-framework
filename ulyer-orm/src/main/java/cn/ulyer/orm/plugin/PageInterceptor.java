package cn.ulyer.orm.plugin;

import cn.ulyer.orm.enums.PluginType;

import java.lang.reflect.InvocationTargetException;

/**
 * 分页
 * @author ulyer
 */

public class PageInterceptor implements OrmInterceptor {

    public PageInterceptor(){}

    @Override
    public PluginType getType() {
        return PluginType.PREPARE;
    }

    @Override
    public Object plugin(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        System.out.println("this is page interceptor");
        return invocation.invoke();
    }


}
