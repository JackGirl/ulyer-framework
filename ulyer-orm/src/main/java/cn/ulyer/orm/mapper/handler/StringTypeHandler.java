package cn.ulyer.orm.mapper.handler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StringTypeHandler implements TypeHandler<String>{
    @Override
    public void setParam(PreparedStatement statement, int index) {

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
