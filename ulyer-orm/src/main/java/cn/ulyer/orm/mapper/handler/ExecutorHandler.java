package cn.ulyer.orm.mapper.handler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface ExecutorHandler {

    int update(PreparedStatement statement, TypeHandler typeHandler);

    ResultSet query(PreparedStatement preparedStatement);

}
