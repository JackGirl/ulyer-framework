package cn.ulyer.orm.parameter;

import cn.ulyer.orm.utils.LogUtils;

import java.sql.PreparedStatement;
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
    public void setParameter(PreparedStatement preparedStatement, String tagSql, ParameterObject parameterObject) {
        try {
            Matcher matcher = pattern.matcher(tagSql);
            int index = 1;
            while (matcher.find()){
                String parameterName = matcher.group();
                String paramName = parameterName.replaceAll("[{}]","");
            }

        }catch (Exception e){
            LogUtils.error(e,"参数解析失败");
        }
    }
}
