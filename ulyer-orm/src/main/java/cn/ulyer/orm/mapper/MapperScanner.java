package cn.ulyer.orm.mapper;

import cn.ulyer.orm.config.OrmConfiguration;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

public interface MapperScanner {

    MapperDefinition register(Class<?> mapperClass);

    MapperDefinition register(InputStream xmlFile);

    Map<String,MapperDefinition> scanner(OrmConfiguration configuration);


}
