package cn.ulyer.orm.mapper.handler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DefaultTypeHandler implements TypeHandler {


    @Override
    public void setParam(PreparedStatement statement, int index) {

    }

    @Override
    public Object getResult(ResultSet resultSet, String columnName) {
        return null;
    }

    @Override
    public Object getResult(ResultSet resultSet, int columnIndex) {
        return null;
    }


}
