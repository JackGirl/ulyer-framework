package cn.ulyer.orm.mapper;

import cn.ulyer.orm.mapper.reader.DefaultReaderFilter;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class SimpleMapperScanner  extends AbstractBaseMapperScanner{

    private DefaultReaderFilter definitionReaderFilter;

    public SimpleMapperScanner(){
        definitionReaderFilter = new DefaultReaderFilter();
    }

    @Override
    public MapperDefinition register(Class<?> mapperClass) {
        String namespace = mapperClass.getName();
        MapperDefinition definition = new MapperDefinition();
        definition.setMapperClass(mapperClass);
        definition.setNamespace(namespace);
        Map<String,MapperMethod> methodMap = new HashMap<>(20);
        Method[] methods = mapperClass.getDeclaredMethods();
        for (Method method : methods) {
           MapperMethod mapperMethod = definitionReaderFilter.read(mapperClass,method);
           methodMap.put(mapperMethod.getId(),mapperMethod);
        }
        definition.setMapperMethodMap(methodMap);
        return definition;
    }



    @Override
    public MapperDefinition register(InputStream file) {
        return null;
    }
}
