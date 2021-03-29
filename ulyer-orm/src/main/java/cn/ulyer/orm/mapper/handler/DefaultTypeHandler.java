package cn.ulyer.orm.mapper.handler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DefaultTypeHandler implements TypeHandler {



    @Override
    public void setParam(PreparedStatement statement, int index, Object val) throws SQLException {

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
