package cn.ulyer.orm.plugin;

import cn.hutool.core.collection.CollectionUtil;
import cn.ulyer.orm.config.OrmConfiguration;
import cn.ulyer.orm.enums.PluginType;
import cn.ulyer.orm.mapper.MapperMethod;
import cn.ulyer.orm.mapper.MapperWrapper;
import cn.ulyer.orm.mapper.ParameterMapping;
import cn.ulyer.orm.mapper.handler.ParameterHandler;
import cn.ulyer.orm.mapper.handler.RegexParameterResolver;
import cn.ulyer.orm.mapper.parameter.ParameterObject;
import cn.ulyer.orm.utils.LogUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 分页
 * @author ulyer
 */

public class PageInterceptor implements OrmInterceptor {

    private PageDialect pageDialect = new MysqlPageDialect();

    private ParameterHandler parameterHandler = new RegexParameterResolver();

    public PageInterceptor(){}

    @Override
    public PluginType getType() {
        return PluginType.PREPARE;
    }

    @Override
    public Object plugin(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        System.out.println("this is page interceptor");
        Connection connection = (Connection) invocation.getArgs()[0];
        MapperWrapper mapperWrapper = (MapperWrapper) invocation.getArgs()[1];
        MapperMethod mapperMethod =  mapperWrapper.getMapperMethod();
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
            if(IPage.class.isAssignableFrom(paramClass)){
                page =  parameterObject.getParameterByName(parameterMapping.getName());
                break;
            }
        }
        if(page!=null){
            try{
                String pageSql = pageDialect.getPageSql(page,mapperMethod.getSql());
                mapperWrapper.setBoundSql(pageSql);
                long total = executeTotalQuery(connection,mapperWrapper);
                page.setTotal(total);
                if(total==0L){
                    return null;
                }
            }catch (Exception e){
                LogUtils.error(e);
            }

        }
        return invocation.invoke();
    }

    private long executeTotalQuery(Connection connection,MapperWrapper mapperWrapper) throws SQLException {
        String query = "select count(1) from ("+mapperWrapper.getBoundSql().replaceAll(OrmConfiguration.PARAM_REGEX,"?")+") t";
        PreparedStatement statement = connection.prepareStatement(query);
        parameterHandler.setParameter(statement,mapperWrapper);
        ResultSet resultSet = statement.executeQuery();
        System.out.println(resultSet);
        LogUtils.info("page execute count:"+query);
        if(resultSet.next()){
            return resultSet.getLong(1);
        }
        return 0L;
    }


}
