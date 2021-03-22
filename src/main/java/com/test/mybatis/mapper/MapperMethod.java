package com.test.mybatis.mapper;

import com.test.mybatis.enums.MapperSqlType;
import lombok.Data;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @Author: yeqi
 * @Description:
 * @Date: Create in 20:54 2021/3/20
 */
@Data
public class MapperMethod {

    private String sql;

    private String id;

    private MapperSqlType mapperSqlType;

    private List<String> parameterNames;

    private Map<String,Object> parameters;

    private Class<?> resultType;


    public static void main(String[] args) {
       Class c = MapperMethod.class;
       Method[]methods  = c.getDeclaredMethods();
        for (Method method : methods) {
            Type returnType = method.getGenericReturnType();
            System.out.println(returnType);
            System.out.println(returnType.getTypeName());
        }

    }

}
