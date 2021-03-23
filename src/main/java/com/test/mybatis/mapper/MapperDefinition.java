package com.test.mybatis.mapper;

import lombok.Data;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @Author: yeqi
 * @Description:
 * @Date: Create in 20:54 2021/3/20
 */
@Data
public class MapperDefinition {

    private String namespace;

    private Class<?> mapperClass;

    private Map<Method,MapperMethod> mapperMethodMap;
}
