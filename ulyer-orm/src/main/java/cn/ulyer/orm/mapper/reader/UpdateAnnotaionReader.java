package cn.ulyer.orm.mapper.reader;

import cn.ulyer.orm.annotation.Update;
import cn.ulyer.orm.enums.MapperSqlType;
import cn.ulyer.orm.mapper.MapperMethod;

import java.lang.reflect.Method;

public class UpdateAnnotaionReader implements AnnotationReader{
    @Override
    public <T> T read(Class<?> mapperClass, Method method) {
        Update update = method.getAnnotation(Update.class);
        if(update!=null){
            MapperMethod mapperMethod = new MapperMethod();
            mapperMethod.setId(mapperClass.getName()+"."+method.getName());
            mapperMethod.setMethod(method);
            mapperMethod.setMapperSqlType(MapperSqlType.UPDATE);
            mapperMethod.setSql(update.sql());
            mapperMethod.setResultType(method.getReturnType());
            return (T) mapperMethod;
        }
        return null;
    }
}
