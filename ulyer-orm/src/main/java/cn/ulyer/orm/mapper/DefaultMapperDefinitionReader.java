package cn.ulyer.orm.mapper;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class DefaultMapperDefinitionReader implements  MapperDefinitionReader{

    @Override
    public MapperDefinition read(Class<?> mapperClass) {
        String namespace = mapperClass.getName();
        MapperDefinition definition = new MapperDefinition();
        definition.setMapperClass(mapperClass);
        definition.setNamespace(namespace);
        Map<String,MapperMethod> methodMap = new HashMap<>(20);
        Method [] methods = mapperClass.getDeclaredMethods();
        for (Method method : methods) {
            MapperMethod mapperMethod = new MapperMethod();
            methodMap.put(namespace+"."+method.getName(),mapperMethod);
        }
        definition.setMapperMethodMap(methodMap);
        return definition;
    }

    @Override
    public MapperDefinition read(InputStream fileStream) {
        return null;
    }
}
