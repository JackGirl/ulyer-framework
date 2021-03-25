package cn.ulyer.orm.mapper;

import cn.ulyer.orm.enums.MapperSqlType;
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
public class MapperMethod {

    private String sql;

    private String id;

    private MapperSqlType mapperSqlType;

    private List<ParameterMapping> parameterMappings;

    private Method method;

    private Map<String,Object> parameters;

    private Class<?> resultType;


}
