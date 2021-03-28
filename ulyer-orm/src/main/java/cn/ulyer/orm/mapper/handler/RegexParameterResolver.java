package cn.ulyer.orm.mapper.handler;

import cn.ulyer.orm.mapper.MapperWrapper;

import java.util.regex.Pattern;

/**
 * @Author: yeqi
 * @Description:
 * @Date: Create in 21:31 2021/3/20
 */
public class RegexParameterResolver implements ParameterHandler {

    Pattern pattern = Pattern.compile("\\{(.+?)\\}");


    @Override
    public void setParameter(MapperWrapper mapperWrapper) {

    }
}
