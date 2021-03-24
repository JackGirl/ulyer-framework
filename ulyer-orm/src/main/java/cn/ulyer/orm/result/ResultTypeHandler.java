package cn.ulyer.orm.result;

import java.sql.ResultSet;

public interface ResultTypeHandler<T> {

    T getResult(ResultSet resultSet,String columnName);

    T getResult(ResultSet resultSet,int columnIndex);


}
