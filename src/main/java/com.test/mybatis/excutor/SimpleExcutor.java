package com.test.mybatis.excutor;

import com.test.connection.DbConnectionUtil;
import com.test.entity.User;
import com.test.mybatis.MapperSql;
import com.test.mybatis.enums.MapperSqlType;
import com.test.mybatis.parameter.ParameterResolver;
import com.test.mybatis.utils.LogUtils;
import lombok.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @Author: yeqi
 * @Description:
 * @Date: Create in 21:03 2021/3/20
 */
@Data
public class SimpleExcutor implements Excutor {

    ParameterResolver parameterResolver;


    @Override
    public Object excutor(MapperSql mapperSql, Map<String,Object> params) {
        Connection connection = DbConnectionUtil.getConnection();
        PreparedStatement statement = parameterResolver.resolverParameter(connection,mapperSql,params);
        ResultSet resultSet = null;
        try {
            connection.setAutoCommit(false);
            if (mapperSql.getMapperSqlType().equals(MapperSqlType.SELECT)){
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
            }else if (mapperSql.getMapperSqlType().equals(MapperSqlType.INSERT)){
                executeUpdate(connection,statement);
            }else if (mapperSql.getMapperSqlType().equals(MapperSqlType.DELETE)){
                executeUpdate(connection,statement);
            }else if (mapperSql.getMapperSqlType().equals(MapperSqlType.UPDATE)){
                executeUpdate(connection,statement);
            }else {
                throw new IllegalStateException("类型异常");
            }
        } catch (Exception e) {
            LogUtils.error(e,"mybatis excutor error");
        }finally {
            DbConnectionUtil.closeConnection(statement,connection);
        }
        return null;
    }

    private int executeUpdate(Connection connection,PreparedStatement statement) throws SQLException {
        int result = statement.executeUpdate();
        connection.commit();
        return result;
    }




}
