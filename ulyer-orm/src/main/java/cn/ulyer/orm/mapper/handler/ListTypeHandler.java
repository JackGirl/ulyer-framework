package cn.ulyer.orm.mapper.handler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ListTypeHandler implements TypeHandler<List>{


    @Override
    public void setParam(PreparedStatement statement, int index, List val) throws SQLException {

    }

    @Override
    public List getResult(ResultSet resultSet, String columnName) {
        return null;
    }

    @Override
    public List getResult(ResultSet resultSet, int columnIndex) {
        return null;
    }
}
