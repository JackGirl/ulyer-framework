package cn.ulyer.orm.mapper.handler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class IntegerTypeHandler implements TypeHandler<Integer>{
    @Override
    public void setParam(PreparedStatement statement, int index) {

    }

    @Override
    public Integer getResult(ResultSet resultSet, String columnName) {
        return null;
    }

    @Override
    public Integer getResult(ResultSet resultSet, int columnIndex) {
        return null;
    }
}
