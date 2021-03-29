package cn.ulyer.orm.excutor;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.ulyer.orm.config.OrmConfiguration;
import cn.ulyer.orm.connection.DatasourceWrapper;
import cn.ulyer.orm.mapper.MapperWrapper;
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

    public AbstractExecutor(OrmConfiguration ormConfiguration){
        this.ormConfiguration = ormConfiguration;
    }


    @Override
    public <T> T execute(final MapperWrapper mapperWrapper) {
        Connection connection = DatasourceWrapper.getConnection();
        try {
            connection.setAutoCommit(false);
            switch (mapperWrapper.getMapperMethod().getMapperSqlType()){
                case SELECT:
                    return executeQuery(mapperWrapper);
                case DELETE:
                case INSERT:
                case UPDATE:
                    return (T) executeUpdate(mapperWrapper);
                default:
                    throw  new IllegalStateException("no  executor type for this mapperSql"+ mapperWrapper.getMapperMethod().getId());
            }
        } catch (Exception e) {
            LogUtils.error(e,"mybatis executor error :"+ ExceptionUtil.stacktraceToString(e));
            throw new RuntimeException("mybatis executor error");
        }
    }





    public  Object executeUpdate(MapperWrapper mapperWrapper) throws SQLException {
        int result = mapperWrapper.getStatement().executeUpdate();
        return result;
    }

    public <T> T executeQuery(MapperWrapper mapperWrapper) throws SQLException{
        ResultSet resultSet ;
        resultSet = mapperWrapper.getStatement().executeQuery();
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
