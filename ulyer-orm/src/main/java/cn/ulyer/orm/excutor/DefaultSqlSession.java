package cn.ulyer.orm.excutor;

import cn.ulyer.orm.config.OrmConfiguration;
import cn.ulyer.orm.mapper.MapperProvider;
import cn.ulyer.orm.mapper.MapperWrapper;
import cn.ulyer.orm.mapper.handler.*;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class DefaultSqlSession implements SqlSession{

    private Executor executor;

    private MapperProvider mapperProvider;

    private OrmConfiguration ormConfiguration;

    private Connection connection;

    public DefaultSqlSession(Executor executor, MapperProvider mapperProvider,OrmConfiguration ormConfiguration,Connection connection){
        this.executor = executor;
        this.mapperProvider = mapperProvider;
        this.ormConfiguration = ormConfiguration;
        this.connection = connection;
    }


    /**
     * 真正的执行方法  包括执行周期也在这里
     * @param namespace
     * @param params
     * @param <T>
     * @return
     */
    @SneakyThrows
    @Override
    public <T> T execute(String namespace, Object... params) {
        MapperWrapper mapperWrapper = mapperProvider.getMapperWrapper(namespace,params);
        //标签根据参数解析

        //生成sql
        StatementHandler statementHandler = ormConfiguration.newStatementHandler(new PrepareStatementHandler());
        PreparedStatement statement = statementHandler.createStatement(connection,mapperWrapper);
        //设置参数
        ParameterHandler parameterHandler = ormConfiguration.newParameterHandler(new RegexParameterResolver());
        parameterHandler.setParameter(statement,mapperWrapper);
        //执行
        Executor executor = ormConfiguration.newExecutor(this.executor);
        //返回包装
        List resultMap =  executor.execute(statement,mapperWrapper);
        TypeHandler typeHandler = ormConfiguration.newResultHandler(new DefaultTypeHandler());
        return (T) resultMap;
    }

    @Override
    public <T> T selectList(String namespace,  Object ...params) {
        return this.execute(namespace,new Object[]{params});
    }

    @Override
    public int update(String namespace,  Object ...params) {
        return this.execute(namespace,new Object[]{params});
    }

    @Override
    public int insert(String namespace,  Object ...params) {
        return this.execute(namespace,new Object[]{params});
    }

    @Override
    public int delete(String namespace, Object ...params) {
        return this.execute(namespace,new Object[]{params});
    }

    @Override
    public Connection getConnection() {
        return connection;
    }







}
