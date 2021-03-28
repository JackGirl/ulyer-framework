package cn.ulyer.orm.mapper.handler;

import java.sql.ResultSet;

public class DefaultTypeHandler implements ResultTypeHandler{


    @Override
    public Object getResult(ResultSet resultSet, String columnName) {
        return null;
    }

    @Override
    public Object getResult(ResultSet resultSet, int columnIndex) {
        return null;
    }


}
