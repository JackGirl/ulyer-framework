package cn.ulyer.orm.excutor;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.lang.Assert;
import cn.ulyer.orm.config.OrmConfiguration;
import cn.ulyer.orm.connection.DatasourceWrapper;
import cn.ulyer.orm.mapper.MapMapperProvider;
import cn.ulyer.orm.mapper.MapperDefinition;
import cn.ulyer.orm.mapper.MapperMethod;
import cn.ulyer.orm.mapper.MapperProvider;
import cn.ulyer.orm.utils.LogUtils;
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


    private MapperProvider mapperProvider;

    private OrmConfiguration ormConfiguration ;

    public AbstractExecutor(OrmConfiguration ormConfiguration){
        this.ormConfiguration = ormConfiguration;
        this.mapperProvider = new MapMapperProvider(ormConfiguration);
    }


    @Override
    public <T> T execute(final MapperMethod mapperMethod, final Map<String, Object> params) {
        Connection connection = DatasourceWrapper.getConnection();
        PreparedStatement statement = null ;
        try {

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
            DatasourceWrapper.closeConnection(statement,connection);
        }
    }



    @Override
    public <T> T execute(String namespace, Map<String, Object> params) {
        MapperDefinition mapperDefinition = mapperProvider.getMapperDefinition(namespace);
        Assert.notNull(mapperDefinition);
        return execute(new MapperMethod(),params);
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
