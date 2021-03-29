package cn.ulyer.orm.mapper;

import cn.ulyer.orm.config.OrmConfiguration;

import java.io.InputStream;
import java.util.Map;

public interface MapperScanner {

    MapperDefinition register(Class<?> mapperClass);

    MapperDefinition register(InputStream xmlFile) throws Exception;

    Map<String,MapperDefinition> scanner(OrmConfiguration configuration);


}
