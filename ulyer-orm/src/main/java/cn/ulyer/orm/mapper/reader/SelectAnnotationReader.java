package cn.ulyer.orm.mapper.reader;

import cn.ulyer.orm.annotation.Select;
import cn.ulyer.orm.enums.MapperSqlType;
import cn.ulyer.orm.mapper.MapperMethod;

import java.lang.reflect.Method;

public class SelectAnnotationReader implements AnnotationReader {

    @Override
    public <T> T read(Class<?> mapperClass, Method method) {
        Select select = method.getAnnotation(Select.class);
        if(select!=null){
            MapperMethod mapperMethod = new MapperMethod();
            mapperMethod.setId(mapperClass.getName()+"."+method.getName());
            mapperMethod.setMethod(method);
            mapperMethod.setMapperSqlType(MapperSqlType.SELECT);
            mapperMethod.setSql(select.sql());
            mapperMethod.setResultType(method.getReturnType());
            return (T) mapperMethod;
        }
        return null;
    }
}
