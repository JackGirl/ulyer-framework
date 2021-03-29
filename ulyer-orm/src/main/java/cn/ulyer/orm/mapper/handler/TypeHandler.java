package cn.ulyer.orm.mapper.handler;


import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface TypeHandler<T> {

    void setParam(PreparedStatement statement,int index);

    T getResult(ResultSet resultSet,String columnName);

    T getResult(ResultSet resultSet,int columnIndex);

}
