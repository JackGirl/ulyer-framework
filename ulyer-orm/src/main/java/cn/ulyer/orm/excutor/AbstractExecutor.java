package cn.ulyer.orm.excutor;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.ulyer.orm.connection.DbConnectionUtil;
import cn.ulyer.orm.config.OrmConfiguration;
import cn.ulyer.orm.enums.PluginType;
import cn.ulyer.orm.mapper.MapperMethod;
import cn.ulyer.orm.parameter.ParameterResolver;
import cn.ulyer.orm.plugin.OrmInterceptor;
import cn.ulyer.orm.result.ResultTypeHandler;
import cn.ulyer.orm.tag.TagResolver;
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

    private ParameterResolver parameterResolver;

    private TagResolver tagResolver;

    private Map<PluginType,List<OrmInterceptor>> pluginMap;

    private Map<Class<?>,ResultTypeHandler<?>> typeHandlers;

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
        PreparedStatement statement = null ;
        try {
            statement  = this.prepare(connection,mapperMethod,params);
            this.parameterResolver.setParameter( statement, mapperMethod.getSql(),params);
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

    public abstract PreparedStatement prepare(Connection connection, MapperMethod mapperMethod, Map<String, Object> params);

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
