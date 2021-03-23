package com.test.mybatis.excutor;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.test.connection.DbConnectionUtil;
import com.test.mybatis.config.OrmConfiguration;
import com.test.mybatis.enums.PluginType;
import com.test.mybatis.mapper.MapperMethod;
import com.test.mybatis.parameter.ParameterResolver;
import com.test.mybatis.plugin.OrmPlugin;
import com.test.mybatis.result.ResultTypeHandler;
import com.test.mybatis.tag.TagResolver;
import com.test.mybatis.utils.LogUtils;
import lombok.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public abstract class AbstractExecutor implements Executor {

    protected OrmConfiguration ormConfiguration ;

    protected ParameterResolver parameterResolver;

    protected TagResolver tagResolver;

    Map<PluginType,List<OrmPlugin>> pluginMap;

    Map<Class<?>,ResultTypeHandler<?>> typeHandlers;

    public AbstractExecutor(OrmConfiguration ormConfiguration){
        this.ormConfiguration = ormConfiguration;
        this.registerPlugins(ormConfiguration);
        this.registerTypeHandlers(ormConfiguration);
    }

    private void registerTypeHandlers(OrmConfiguration ormConfiguration) {
    }

    protected  void registerPlugins(OrmConfiguration ormConfiguration){

    }

    @Override
    public <T> T execute(final MapperMethod mapperMethod, final Map<String, Object> params) {
        Connection connection = DbConnectionUtil.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {

            statement  = connection.prepareStatement(mapperMethod.getSql().replaceAll("\\{(.+?)\\}"," ? "));
            this.getParameterResolver().setParameter(statement, mapperMethod.getSql(),params);
            connection.setAutoCommit(false);
            switch (mapperMethod.getMapperSqlType()){
                case SELECT:
                    return executeQuery(connection,statement);
                case DELETE:
                case INSERT:
                case UPDATE:
                    return (T) executeUpdate(connection,statement);
                default:
                    throw  new IllegalStateException("no  excutor type for this mapperSql"+ mapperMethod.getId());
            }
        } catch (Exception e) {
            LogUtils.error(e,"mybatis excutor error :"+ ExceptionUtil.stacktraceToString(e));
            throw new RuntimeException("mybatis excutor error");
        }finally {
            DbConnectionUtil.closeConnection(statement,connection);
        }
    }

    public ParameterResolver getParameterResolver(){
        return this.parameterResolver;
    }




    public  Object executeUpdate(Connection connection, PreparedStatement statement) throws SQLException {
        int result = statement.executeUpdate();
        connection.commit();
        return result;
    }

    public <T> T executeQuery(Connection connection,PreparedStatement statement) throws SQLException{
        ResultSet resultSet ;
        connection.setReadOnly(true);
        resultSet = statement.executeQuery();
        int columnCount = resultSet.getMetaData().getColumnCount();
        List<Object> list = new ArrayList<>();
        while (resultSet.next()){
            Map<String, Object> map = new HashMap<>();
            for (int i = 0; i < columnCount; i++) {
                String columnName = resultSet.getMetaData().getColumnName(i+1);
                String str = resultSet.getString(i+1);
                map.put(columnName,str);
            }
            list.add(map);
        }
        return (T) list;
    }



}
