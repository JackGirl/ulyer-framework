package com.test.mybatis.parameter;

import com.test.mybatis.MapperSql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;

/**
 * @Author: yeqi
 * @Description:
 * @Date: Create in 21:20 2021/3/20
 */
public interface ParameterResolver {

    PreparedStatement resolverParameter(Connection connection, MapperSql mapperSql, Map<String, Object> params);


}
