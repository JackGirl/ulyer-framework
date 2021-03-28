package cn.ulyer.orm.plugin.handler;

import cn.ulyer.orm.mapper.MapperWrapper;
import cn.ulyer.orm.parameter.ParameterObject;

import java.sql.PreparedStatement;

public interface ParameterHandler {

    void setParameter(PreparedStatement preparedStatement, MapperWrapper mapperWrapper);


}
