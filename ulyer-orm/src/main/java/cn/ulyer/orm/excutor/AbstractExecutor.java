package cn.ulyer.orm.excutor;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.ulyer.orm.config.OrmConfiguration;
import cn.ulyer.orm.connection.DatasourceWrapper;
import cn.ulyer.orm.mapper.MapperWrapper;
import cn.ulyer.orm.mapper.handler.*;
import cn.ulyer.orm.utils.LogUtils;
import lombok.Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public abstract class AbstractExecutor implements Executor {


    private OrmConfiguration ormConfiguration ;

    private Connection connection;

    private StatementHandler statementHandler ;

    private ExecutorHandler executorHandler;

    public AbstractExecutor(Connection connection,OrmConfiguration ormConfiguration){
        this.ormConfiguration = ormConfiguration;
        this.connection = connection;
        this.statementHandler = new PrepareStatementHandler();
        executorHandler = ormConfiguration.newExecutorHandler(new DefaultExecutorHandler());
    }




    @Override
    public <T> T execute( MapperWrapper mapperWrapper) {
        PreparedStatement statement = preparedStatement(this.connection,mapperWrapper);
        //执行
        //返回包装
        try {
            connection.setAutoCommit(false);
            switch (mapperWrapper.getMapperMethod().getMapperSqlType()){
                case SELECT:
                   ResultSet resultSet = this.executorHandler.query(statement);
                    return resolverResultSet(resultSet,mapperWrapper);
                case DELETE:
                case INSERT:
                case UPDATE:
                    Object count = Integer.valueOf(executorHandler.update(statement,null));
                    return (T)count;
                default:
                    throw  new IllegalStateException("no  executor type for this mapperSql"+ mapperWrapper.getMapperMethod().getId());
            }
        } catch (Exception e) {
            LogUtils.error(e,"mybatis executor error :"+ ExceptionUtil.stacktraceToString(e));
            throw new RuntimeException("mybatis executor error");
        }
    }



    @Override
    public <E> List<E> selectList(MapperWrapper mapperWrapper) {
        PreparedStatement statement = preparedStatement(this.connection,mapperWrapper);
        ResultSet resultSet = this.executorHandler.query(statement);
        return resolverResultSet(resultSet,mapperWrapper);
    }

    @Override
    public <T> T selectOne(MapperWrapper mapperWrapper) {
        PreparedStatement statement = preparedStatement(this.connection,mapperWrapper);
        ResultSet resultSet = this.executorHandler.query(statement);
        return resolverResultSet(resultSet,mapperWrapper);
    }

    @Override
    public int insert(MapperWrapper mapperWrapper) {
        PreparedStatement statement = preparedStatement(this.connection,mapperWrapper);
         return this.executorHandler.update(statement,null);

    }

    @Override
    public int update(MapperWrapper mapperWrapper) {
        PreparedStatement statement = preparedStatement(this.connection,mapperWrapper);
        return this.executorHandler.update(statement,null);
    }

    @Override
    public int delete(MapperWrapper mapperWrapper) {
        PreparedStatement statement = preparedStatement(this.connection,mapperWrapper);
        return this.executorHandler.update(statement,null);
    }

    public <T>T resolverResultSet(ResultSet resultSet,MapperWrapper mapperWrapper){
        Class resultType = mapperWrapper.getMapperMethod().getResultType();
        try {
            if(resultSet.getRow()>1){
                List list = new ArrayList();
                while (resultSet.next()){

                }
            }
        } catch (SQLException e) {
            LogUtils.error(e);
            throw new RuntimeException("resolver result error");
        }
        return null;
    }

    public PreparedStatement preparedStatement(Connection connection,MapperWrapper mapperWrapper){
        PreparedStatement statement = null;
        try{
            //标签根据参数解析
            //生成sql
            StatementHandler statementHandler = ormConfiguration.newStatementHandler(this.statementHandler);
            statement = statementHandler.createStatement(connection,mapperWrapper);
            //设置参数
            ParameterHandler parameterHandler = ormConfiguration.newParameterHandler(new RegexParameterResolver());
            parameterHandler.setParameter(statement,mapperWrapper);
        }catch (Exception e){
            e.printStackTrace();
        }
        return statement;
    }



}
