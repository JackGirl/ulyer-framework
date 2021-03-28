package cn.ulyer.orm.mapper.handler;

import cn.ulyer.orm.mapper.MapperWrapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class PrepareStatementHandler implements StatementHandler{

    private Connection connection;

    public PrepareStatementHandler(Connection connection) {
        this.connection = connection;
    }

    @Override
    public PreparedStatement createStatement(MapperWrapper mapperWrapper) {
        return null;
    }
}
