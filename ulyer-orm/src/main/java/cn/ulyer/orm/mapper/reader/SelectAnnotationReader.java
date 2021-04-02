package cn.ulyer.orm.mapper.reader;

import cn.ulyer.orm.annotation.Select;
import cn.ulyer.orm.enums.MapperSqlType;
import cn.ulyer.orm.mapper.MapperMethod;

import java.lang.reflect.Method;

public class SelectAnnotationReader implements AnnotationReader {


    @Override
    public void read(Class<?> mapperClass, Method method, MapperMethod mapperMethod) {
        Select select = method.getAnnotation(Select.class);
        if(select!=null) {
            mapperMethod.setMapperSqlType(MapperSqlType.SELECT);
            mapperMethod.setXml("<root>"+select.sql()+"</root>");
        }
    }
}
