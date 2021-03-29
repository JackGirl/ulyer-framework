package cn.ulyer.orm.plugin;

import cn.ulyer.orm.enums.PluginType;

import java.lang.reflect.InvocationTargetException;

public class PreparePlugin  implements OrmInterceptor{


    @Override
    public PluginType getType() {
        return PluginType.PREPARE;
    }

    @Override
    public Object plugin(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        System.out.println(111111);
        return invocation.invoke();
    }
}
