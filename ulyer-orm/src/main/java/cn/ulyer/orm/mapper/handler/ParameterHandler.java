package cn.ulyer.orm.mapper.handler;

import cn.ulyer.orm.mapper.MapperWrapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public interface ParameterHandler {

    void setParameter(PreparedStatement statement, MapperWrapper mapperWrapper) throws SQLException;


}
