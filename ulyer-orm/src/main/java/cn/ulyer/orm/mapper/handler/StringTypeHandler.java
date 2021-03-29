package cn.ulyer.orm.mapper.handler;

import lombok.SneakyThrows;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StringTypeHandler implements TypeHandler<String>{
    @Override
    public void setParam(PreparedStatement statement, int index,String val) throws SQLException {
        statement.setString(index,val);
    }

    @SneakyThrows
    @Override
    public String getResult(ResultSet resultSet, String columnName) {
        try {
            return resultSet.getString(columnName);
        } catch (SQLException e) {
            throw new IllegalAccessException("no such value for column :"+columnName);
        }
    }

    @SneakyThrows
    @Override
    public String getResult(ResultSet resultSet, int columnIndex)  {
        try {
            return resultSet.getString(columnIndex);
        } catch (SQLException e) {
            throw new IllegalAccessException("no such value for column index:"+columnIndex);
        }
    }
}
