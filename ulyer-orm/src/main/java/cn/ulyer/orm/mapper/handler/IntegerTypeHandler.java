package cn.ulyer.orm.mapper.handler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IntegerTypeHandler implements TypeHandler<Integer>{


    @Override
    public void setParam(PreparedStatement statement, int index, Integer val) throws SQLException {

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
