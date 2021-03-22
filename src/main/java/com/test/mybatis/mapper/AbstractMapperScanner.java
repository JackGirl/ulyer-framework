package com.test.mybatis.mapper;

import com.test.mybatis.factory.OrmConfiguration;

import java.util.Map;

public abstract class AbstractMapperScanner implements MapperScanner {


    private Map<String , MapperDefinition> mappers;


    @Override
    public void register(Class<?> mapperClass) {
        this.mappers.put(mapperClass.getName(),this.createMapperDefinition(mapperClass));
    }

    protected abstract MapperDefinition createMapperDefinition(Class<?> mapperClass);

    @Override
    public void scanner(OrmConfiguration configuration) {

    }


    @Override
    public MapperDefinition getMapperDefinitions(Class<?> mapperClass) {
        return null;
    }

    @Override
    public Map<Class<?>, MapperDefinition> getDefinitions() {
        return null;
    }


}
