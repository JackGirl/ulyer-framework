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

    public AbstractExecutor(OrmConfiguration ormConfiguration){
        this.ormConfiguration = ormConfiguration;
    }

    public PreparedStatement preparedStatement(Connection connection,MapperWrapper mapperWrapper){
        PreparedStatement statement = null;
        try{
            //标签根据参数解析

            //生成sql
            StatementHandler statementHandler = ormConfiguration.newStatementHandler(new PrepareStatementHandler());
            statement = statementHandler.createStatement(connection,mapperWrapper);
            //设置参数
            ParameterHandler parameterHandler = ormConfiguration.newParameterHandler(new RegexParameterResolver());
            parameterHandler.setParameter(statement,mapperWrapper);
        }catch (Exception e){
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    public <T> T execute( MapperWrapper mapperWrapper) throws SQLException {
        Connection connection = DatasourceWrapper.getConnection();
        //执行
        SimpleExecutor executor = (SimpleExecutor) ormConfiguration.newExecutor(this);
        //返回包装
        try {
            connection.setAutoCommit(false);
            switch (mapperWrapper.getMapperMethod().getMapperSqlType()){
                case SELECT:
                    return executor.executeQuery(preparedStatement(connection,mapperWrapper),mapperWrapper);
                case DELETE:
                case INSERT:
                case UPDATE:
                    return (T) executeUpdate(preparedStatement(connection,mapperWrapper),mapperWrapper);
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

        return null;
    }

    @Override
    public <T> T selectOne(MapperWrapper mapperWrapper) {
        return null;
    }

    @Override
    public int insert(MapperWrapper mapperWrapper) {
        return 0;
    }

    @Override
    public int update(MapperWrapper mapperWrapper) {
        return 0;
    }

    @Override
    public int delete(MapperWrapper mapperWrapper) {
        return 0;
    }

    public  Integer executeUpdate(PreparedStatement statement, MapperWrapper mapperWrapper) throws SQLException {
        int result = statement.executeUpdate();
        return result;
    }

    public <T> T executeQuery(PreparedStatement statement,MapperWrapper mapperWrapper) throws SQLException{
        ResultSet resultSet ;
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
