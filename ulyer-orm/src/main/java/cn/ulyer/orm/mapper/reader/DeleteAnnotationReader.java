package cn.ulyer.orm.mapper.reader;

import cn.ulyer.orm.annotation.Delete;
import cn.ulyer.orm.enums.MapperSqlType;
import cn.ulyer.orm.mapper.MapperMethod;

import java.lang.reflect.Method;

public class DeleteAnnotationReader implements AnnotationReader{


    @Override
    public void read(Class<?> mapperClass, Method method, MapperMethod mapperMethod) {
        Delete delete = method.getAnnotation(Delete.class);
        if(delete!=null){
            mapperMethod.setMapperSqlType(MapperSqlType.DELETE);
            mapperMethod.setSql(delete.sql());
        }
    }
}
