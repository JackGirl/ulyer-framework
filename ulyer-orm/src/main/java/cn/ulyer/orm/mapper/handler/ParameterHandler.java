package cn.ulyer.orm.mapper.handler;

import cn.ulyer.orm.mapper.MapperWrapper;

import java.sql.PreparedStatement;

public interface ParameterHandler {

    void setParameter( MapperWrapper mapperWrapper);


}
