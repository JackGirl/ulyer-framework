package com.test.mybatis.mapper;

import com.test.mybatis.factory.OrmConfiguration;

import java.util.Map;

public interface MapperScanner {

    void register(Class<?> mapperClass);

    void scanner(OrmConfiguration configuration);

    MapperDefinition getMapperDefinitions(Class<?> mapperClass);

    Map<Class<?>,MapperDefinition> getDefinitions();
}
