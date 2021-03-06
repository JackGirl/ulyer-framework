package cn.ulyer.orm.mapper;

import cn.ulyer.orm.mapper.reader.AnnotationReader;
import cn.ulyer.orm.mapper.reader.DefaultReaderFilter;
import cn.ulyer.orm.mapper.reader.OrmFileReader;
import cn.ulyer.orm.mapper.reader.XmlReader;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class SimpleMapperScanner  extends AbstractBaseMapperScanner{

    private AnnotationReader definitionReaderFilter;

    private OrmFileReader ormFileReader;
    public SimpleMapperScanner(){
        definitionReaderFilter = new DefaultReaderFilter();
        ormFileReader = new XmlReader();
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
            MapperMethod mapperMethod = new MapperMethod();
            mapperMethod.setId(mapperClass.getName()+"."+method.getName());
            mapperMethod.setMethod(method);
            mapperMethod.setResultType(method.getReturnType());
            definitionReaderFilter.read(mapperClass,method,mapperMethod);
           methodMap.put(mapperMethod.getId(),mapperMethod);
        }
        definition.setMapperMethodMap(methodMap);
        return definition;
    }




    @Override
    public MapperDefinition register(InputStream file) throws Exception {
        return ormFileReader.readDefinition(file);
    }
}
