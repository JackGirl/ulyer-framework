package com.test.mybatis.excutor;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.test.connection.DbConnectionUtil;
import com.test.mybatis.mapper.Mapper;
import com.test.mybatis.parameter.ParameterResolver;
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

/**
 * @Author: yeqi
 * @Description:
 * @Date: Create in 21:03 2021/3/20
 */
@Data
public class SimpleExecutor implements Executor {

    ParameterResolver parameterResolver;

    TagResolver tagResolver;

    Map<Class<?>,ResultTypeHandler> typeHandlers;


    @Override
    public Object execute(Mapper mapper, Map<String,Object> params) {
        Connection connection = DbConnectionUtil.getConnection();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement  = connection.prepareStatement(mapper.getSql().replaceAll("\\{(.+?)\\}"," ? "));
            parameterResolver.setParameter(statement, mapper.getSql(),params);
            connection.setAutoCommit(false);
            switch (mapper.getMapperSqlType()){
                case SELECT:
                    return executeQuery(connection,statement);
                case DELETE:
                case INSERT:
                case UPDATE:
                    return executeUpdate(connection,statement);
                default:
                    throw  new IllegalStateException("no  excutor type for this mapperSql"+ mapper.getId());
            }
        } catch (Exception e) {
            LogUtils.error(e,"mybatis excutor error :"+ ExceptionUtil.stacktraceToString(e));
            throw new RuntimeException("mybatis excutor error");
        }finally {
            DbConnectionUtil.closeConnection(statement,connection);
        }
    }

    public int executeUpdate(Connection connection,PreparedStatement statement) throws SQLException {
        int result = statement.executeUpdate();
        connection.commit();
        return result;
    }

    public Object executeQuery(Connection connection,PreparedStatement statement) throws SQLException{
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
        return list;
    }






}
