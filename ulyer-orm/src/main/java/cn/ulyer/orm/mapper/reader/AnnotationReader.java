package cn.ulyer.orm.mapper.reader;

import java.lang.reflect.Method;

public interface AnnotationReader {

    <T>T read(Class<?> mapperClass, Method method);

}

