package cn.ulyer.orm.mapper.handler;

import cn.hutool.core.lang.Assert;
import cn.ulyer.orm.config.OrmConfiguration;
import cn.ulyer.orm.mapper.MapperWrapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;

/**
 * @Author: yeqi
 * @Description:
 * @Date: Create in 21:31 2021/3/20
 */
public class RegexParameterResolver implements ParameterHandler {


    /**
     * 这里可以根据参数类型选择typeHandler  解析指定jdbcType处理等
     * @param mapperWrapper
     * @throws SQLException
     */
    @Override
    public void setParameter(PreparedStatement statement,MapperWrapper mapperWrapper) throws SQLException {
        String boundSql = mapperWrapper.getBoundSql();
        PreparedStatement preparedStatement = statement;
        Assert.notNull(preparedStatement);
        Matcher matcher = OrmConfiguration.PARAM_REGEX_PATTERN.matcher(boundSql);
        int i = 1;
        while (matcher.find()){
            String paramPattern = matcher.group();
            Object paramVal = mapperWrapper.getParameterObject().getParameterByName(paramPattern.replace("",""));
            TypeHandler typeHandler = mapperWrapper.getOrmConfiguration().getTypeHandler(paramVal.getClass());
            typeHandler.setParam(preparedStatement,i,paramVal);
            i++;
        }
    }
}
