package cn.ulyer.orm.parameter;

import cn.ulyer.orm.mapper.MapperWrapper;
import cn.ulyer.orm.plugin.handler.ParameterHandler;
import cn.ulyer.orm.utils.LogUtils;

import java.sql.PreparedStatement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: yeqi
 * @Description:
 * @Date: Create in 21:31 2021/3/20
 */
public class RegexParameterResolver implements ParameterHandler {

    Pattern pattern = Pattern.compile("\\{(.+?)\\}");



    @Override
    public void setParameter(PreparedStatement preparedStatement, MapperWrapper mapperWrapper) {

    }
}
