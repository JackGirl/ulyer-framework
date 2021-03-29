package cn.ulyer.orm.mapper.handler;

import cn.ulyer.orm.mapper.MapperWrapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementHandler {

    PreparedStatement createStatement(Connection connection,MapperWrapper mapperWrapper) throws SQLException;


}
