package cn.ulyer.orm.mapper.handler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StringTypeHandler implements TypeHandler<String>{
    @Override
    public void setParam(PreparedStatement statement, int index,String val) throws SQLException {
        statement.setString(index,val);
    }

    @Override
    public String getResult(ResultSet resultSet, String columnName) {
        return null;
    }

    @Override
    public String getResult(ResultSet resultSet, int columnIndex) {
        return null;
    }
}
