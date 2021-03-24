package cn.ulyer.orm.mapper;

import lombok.Data;

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

    private Map<String,MapperMethod> mapperMethodMap;
}
