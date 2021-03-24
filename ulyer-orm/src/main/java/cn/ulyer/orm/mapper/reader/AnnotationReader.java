package cn.ulyer.orm.mapper.reader;

import cn.ulyer.orm.mapper.MapperMethod;

import java.lang.reflect.Method;

public interface AnnotationReader {

    void read(Class<?> mapperClass, Method method, MapperMethod mapperMethod);

}

