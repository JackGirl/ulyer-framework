package cn.ulyer.orm.mapper.reader;

import cn.ulyer.orm.annotation.Insert;
import cn.ulyer.orm.annotation.Update;
import cn.ulyer.orm.enums.MapperSqlType;
import cn.ulyer.orm.mapper.MapperMethod;

import java.lang.reflect.Method;

public class InsertAnnotaionReader implements AnnotationReader {

    @Override
    public <T> T read(Class<?> mapperClass, Method method) {
        Insert insert = method.getAnnotation(Insert.class);
        if(insert!=null){
            MapperMethod mapperMethod = new MapperMethod();
            mapperMethod.setId(mapperClass.getName()+"."+method.getName());
            mapperMethod.setMethod(method);
            mapperMethod.setMapperSqlType(MapperSqlType.INSERT);
            mapperMethod.setSql(insert.sql());
            mapperMethod.setResultType(method.getReturnType());
            return (T) mapperMethod;
        }
        return null;
    }
}
