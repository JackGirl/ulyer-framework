package cn.ulyer.orm.plugin;

import cn.hutool.core.collection.CollectionUtil;
import cn.ulyer.orm.enums.PluginType;
import cn.ulyer.orm.mapper.MapperMethod;
import cn.ulyer.orm.mapper.MapperWrapper;
import cn.ulyer.orm.mapper.ParameterMapping;
import cn.ulyer.orm.mapper.parameter.ParameterObject;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

/**
 * 分页
 * @author ulyer
 */

public class PageInterceptor implements OrmInterceptor {

    private PageDialect pageDialect;

    public PageInterceptor(){}

    @Override
    public PluginType getType() {
        return PluginType.PREPARE;
    }

    @Override
    public Object plugin(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        MapperWrapper mapperWrapper = (MapperWrapper) invocation.getArgs()[0];
        MapperMethod mapperMethod = (MapperMethod) mapperWrapper.getMapperMethod();
        ParameterObject parameterObject = mapperWrapper.getParameterObject();
        List<ParameterMapping> parameterMappings = mapperMethod.getParameterMappings();
        if(CollectionUtil.isEmpty(parameterMappings)){
            //执行下一个拦截器
            return invocation.invoke();
        }
        Page page =null;
        for (int i = 0; i < parameterMappings.size(); i++) {
            ParameterMapping parameterMapping = parameterMappings.get(i);
            Class<?> paramClass = parameterMapping.getType();
            if(Page.class.isAssignableFrom(paramClass)){
                page =  parameterObject.getParameterByName(parameterMapping.getName());
                break;
            }
        }
        if(page!=null){
            String pageSql = pageDialect.getPageSql(page,mapperMethod.getSql());
        }

        System.out.println("this is page interceptor");
        return invocation.invoke();
    }


}
