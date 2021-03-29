package cn.ulyer.orm.mapper.handler;

import cn.ulyer.orm.config.OrmConfiguration;
import cn.ulyer.orm.mapper.MapperWrapper;
import lombok.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Data
public class PrepareStatementHandler implements StatementHandler{

    @Override
    public PreparedStatement createStatement(Connection connection,MapperWrapper mapperWrapper) throws SQLException {
       return connection.prepareStatement(mapperWrapper.getBoundSql().replaceAll(OrmConfiguration.PARAM_REGEX," ? "));
    }
}
