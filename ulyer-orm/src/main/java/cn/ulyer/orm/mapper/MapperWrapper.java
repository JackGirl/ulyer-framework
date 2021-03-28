package cn.ulyer.orm.mapper;

import lombok.Data;

import java.util.Map;

@Data
public class MapperWrapper {

    private String boundSql;

    private MapperDefinition mapperDefinition;

    private MapperMethod mapperMethod;

    private Map<String,Object> parameters;

}
