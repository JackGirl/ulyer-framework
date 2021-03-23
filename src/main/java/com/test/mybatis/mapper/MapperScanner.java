package com.test.mybatis.mapper;

import com.test.mybatis.config.OrmConfiguration;

import java.io.InputStream;
import java.util.Map;

public interface MapperScanner {

    MapperDefinition register(Class<?> mapperClass);

    MapperDefinition register(InputStream stream);

    Map<String,MapperDefinition> scanner(OrmConfiguration configuration);


}
