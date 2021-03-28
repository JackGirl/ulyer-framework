package cn.ulyer.orm.excutor;

import cn.ulyer.orm.config.OrmConfiguration;
import cn.ulyer.orm.mapper.MapperProvider;
import cn.ulyer.orm.mapper.MapperWrapper;
import cn.ulyer.orm.mapper.handler.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class DefaultSqlSession implements SqlSession{

    private Executor executor;

    private MapperProvider mapperProvider;

    private OrmConfiguration ormConfiguration;

    public DefaultSqlSession(Executor executor, MapperProvider mapperProvider,OrmConfiguration ormConfiguration){
        this.executor = executor;
        this.mapperProvider = mapperProvider;
        this.ormConfiguration = ormConfiguration;
    }



    @Override
    public <T> T selectList(String namespace,  Object ...params) {
        MapperWrapper mapperWrapper = mapperProvider.getMapperWrapper(namespace,params);
        StatementHandler statementHandler = ormConfiguration.newStatementHandler(new PrepareStatementHandler());
        PreparedStatement statement = statementHandler.createStatement(mapperWrapper);
        mapperWrapper.setStatement(statement);
        ParameterHandler parameterHandler = ormConfiguration.newParameterHandler(new RegexParameterResolver());
        parameterHandler.setParameter(mapperWrapper);
        Executor executor = ormConfiguration.newExecutor(this.executor);
        ResultSet resultMap =  executor.execute(mapperWrapper);
        ResultTypeHandler resultTypeHandler = ormConfiguration.newResultHandler(new DefaultTypeHandler());

        return null;
    }

    @Override
    public int update(String namespace,  Object ...params) {
        return 0;
    }

    @Override
    public int insert(String namespace,  Object ...params) {
        return 0;
    }

    @Override
    public int delete(String namespace, Object ...params) {
        return 0;
    }

    @Override
    public Connection getConnection() {
        return null;
    }





}
