package cn.ulyer.orm.mapper;

import cn.ulyer.orm.parameter.ParameterObject;
import lombok.Data;

import java.util.Map;

@Data
public class MapperWrapper {

    private String boundSql;

    private MapperDefinition mapperDefinition;

    private MapperMethod mapperMethod;

    private ParameterObject parameterObject;

}
