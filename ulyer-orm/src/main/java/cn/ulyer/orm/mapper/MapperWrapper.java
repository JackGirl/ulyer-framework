package cn.ulyer.orm.mapper;

import cn.ulyer.orm.config.OrmConfiguration;
import cn.ulyer.orm.mapper.parameter.ParameterObject;
import lombok.Data;

import java.sql.PreparedStatement;

@Data
public class MapperWrapper {

    private String boundSql;

    private MapperDefinition mapperDefinition;

    private MapperMethod mapperMethod;

    private ParameterObject parameterObject;

    private OrmConfiguration ormConfiguration;


}
