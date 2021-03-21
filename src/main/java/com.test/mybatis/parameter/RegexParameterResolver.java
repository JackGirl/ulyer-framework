package com.test.mybatis.parameter;

import com.test.mybatis.MapperSql;
import com.test.mybatis.utils.LogUtils;

import java.sql.Connection;
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
    public PreparedStatement resolverParameter(Connection connection,MapperSql mapperSql, Map<String, Object> params) {
        try {
            Matcher matcher = pattern.matcher(mapperSql.getSql());
            List<String> parameterList = new ArrayList<>();
            while (matcher.find()){
                String parameterName = matcher.group();
                parameterList.add(parameterName.replaceAll("[{}]",""));
                mapperSql.setSql( mapperSql.getSql().replace(parameterName," ? "));
            }
            PreparedStatement preparedStatement =  connection.prepareStatement(mapperSql.getSql());
            for (int i = 0; i < parameterList.size() ; i++) {
                String value = (String) params.get(parameterList.get(i));
                preparedStatement.setString(i+1,value );
            }
            return preparedStatement;
        }catch (Exception e){
            LogUtils.error(e,"参数解析失败");
            return null;
        }
    }





}
