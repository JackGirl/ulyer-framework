package cn.ulyer.orm.mapper.handler;

import cn.ulyer.orm.mapper.MapperWrapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public interface StatementHandler {

    PreparedStatement createStatement(MapperWrapper mapperWrapper) throws SQLException;

}
