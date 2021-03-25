package cn.ulyer.orm.plugin;

import cn.ulyer.orm.enums.PluginType;

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
    public Object plugin(Invocation invocation) {
        return null;
    }


}
