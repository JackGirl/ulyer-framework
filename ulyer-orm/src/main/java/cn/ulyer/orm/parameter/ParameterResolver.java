package cn.ulyer.orm.parameter;

import java.sql.PreparedStatement;

/**
 * @Author: yeqi
 * @Description:
 * @Date: Create in 21:20 2021/3/20
 */
public interface ParameterResolver {

    void setParameter(PreparedStatement preparedStatement, String tagSql, ParameterObject parameterObject);


}
