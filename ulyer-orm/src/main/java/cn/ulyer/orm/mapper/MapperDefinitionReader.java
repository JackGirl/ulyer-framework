package cn.ulyer.orm.mapper;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;

public interface MapperDefinitionReader {

    MapperDefinition read(Class<?> mapperClass);

    MapperDefinition read(InputStream fileStream);

}
