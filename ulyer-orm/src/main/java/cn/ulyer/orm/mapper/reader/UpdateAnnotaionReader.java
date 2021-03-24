package cn.ulyer.orm.mapper.reader;

import cn.ulyer.orm.annotation.Update;
import cn.ulyer.orm.enums.MapperSqlType;
import cn.ulyer.orm.mapper.MapperMethod;

import java.lang.reflect.Method;

public class UpdateAnnotaionReader implements AnnotationReader{


    @Override
    public void read(Class<?> mapperClass, Method method, MapperMethod mapperMethod) {
        Update update = method.getAnnotation(Update.class);
        if(update!=null) {
            mapperMethod.setMapperSqlType(MapperSqlType.UPDATE);
            mapperMethod.setSql(update.sql());
        }
    }
}
