package cn.ulyer.orm.mapper.reader;

import cn.ulyer.orm.annotation.Delete;
import cn.ulyer.orm.annotation.Insert;
import cn.ulyer.orm.enums.MapperSqlType;
import cn.ulyer.orm.mapper.MapperMethod;

import java.lang.reflect.Method;

public class DeleteAnnotationReader implements AnnotationReader{
    @Override
    public <T> T read(Class<?> mapperClass, Method method) {
        Delete delete = method.getAnnotation(Delete.class);
        if(delete!=null){
            MapperMethod mapperMethod = new MapperMethod();
            mapperMethod.setId(mapperClass.getName()+"."+method.getName());
            mapperMethod.setMethod(method);
            mapperMethod.setMapperSqlType(MapperSqlType.DELETE);
            mapperMethod.setSql(delete.sql());
            mapperMethod.setResultType(method.getReturnType());
            return (T) mapperMethod;
        }
        return null;
    }
}
