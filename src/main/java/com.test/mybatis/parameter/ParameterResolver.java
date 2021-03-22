package com.test.mybatis.parameter;

import java.sql.PreparedStatement;
import java.util.Map;

/**
 * @Author: yeqi
 * @Description:
 * @Date: Create in 21:20 2021/3/20
 */
public interface ParameterResolver {

    void setParameter( PreparedStatement preparedStatement,String tagSql,Map<String,Object> params);


}
