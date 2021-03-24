package cn.ulyer.orm.mapper.reader;

import cn.ulyer.orm.annotation.Insert;
import cn.ulyer.orm.annotation.Update;
import cn.ulyer.orm.enums.MapperSqlType;
import cn.ulyer.orm.mapper.MapperMethod;

import java.lang.reflect.Method;

public class InsertAnnotaionReader implements AnnotationReader {



    @Override
    public void read(Class<?> mapperClass, Method method, MapperMethod mapperMethod) {
        Insert insert = method.getAnnotation(Insert.class);
        if(insert!=null){
            mapperMethod.setMapperSqlType(MapperSqlType.INSERT);
            mapperMethod.setSql(insert.sql());
        }
    }
}
