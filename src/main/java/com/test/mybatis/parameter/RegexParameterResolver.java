package com.test.mybatis.parameter;

import com.test.mybatis.utils.LogUtils;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: yeqi
 * @Description:
 * @Date: Create in 21:31 2021/3/20
 */
public class RegexParameterResolver implements ParameterResolver {

    Pattern pattern = Pattern.compile("\\{(.+?)\\}");


    @Override
    public void setParameter(PreparedStatement preparedStatement, String tagSql, Map<String, Object> params) {
        try {
            Matcher matcher = pattern.matcher(tagSql);
            int index = 1;
            while (matcher.find()){
                String parameterName = matcher.group();
                String paramName = parameterName.replaceAll("[{}]","");
                preparedStatement.setString(index, (String) params.get(paramName));
            }

        }catch (Exception e){
            LogUtils.error(e,"参数解析失败");
        }
    }
}
