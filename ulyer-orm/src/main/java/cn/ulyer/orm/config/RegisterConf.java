package cn.ulyer.orm.config;

import cn.hutool.core.lang.Assert;
import cn.ulyer.orm.mapper.handler.IntegerTypeHandler;
import cn.ulyer.orm.mapper.handler.ListTypeHandler;
import cn.ulyer.orm.mapper.handler.StringTypeHandler;
import cn.ulyer.orm.mapper.handler.TypeHandler;
import cn.ulyer.orm.tag.IFTagResolver;
import cn.ulyer.orm.tag.TagResolver;
import cn.ulyer.orm.tag.WhereTagResolver;

import java.sql.JDBCType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterConf {


    Map<Class<?>, TypeHandler> typeHandlerMap = new HashMap<>();

    Map<JDBCType, TypeHandler> jdbcTypeTypeHandlers = new HashMap<>();

    Map<String,TagResolver> tagResolvers = new HashMap<>();

    public RegisterConf() {
        this.registerTypeHandlers();
        this.registerTagResolvers();
    }




    private void registerTypeHandlers() {
        //typeHandlerMap.put()
        StringTypeHandler stringTypeHandler = new StringTypeHandler();
        typeHandlerMap.put(String.class, stringTypeHandler);
        typeHandlerMap.put(Integer.class, new IntegerTypeHandler());
        typeHandlerMap.put(int.class, new IntegerTypeHandler());
        typeHandlerMap.put(List.class, new ListTypeHandler());
        jdbcTypeTypeHandlers.put(JDBCType.CHAR,stringTypeHandler);
        jdbcTypeTypeHandlers.put(JDBCType.INTEGER,new IntegerTypeHandler());
        jdbcTypeTypeHandlers.put(JDBCType.VARCHAR,stringTypeHandler);
        jdbcTypeTypeHandlers.put(JDBCType.NCHAR,stringTypeHandler);
        jdbcTypeTypeHandlers.put(JDBCType.NVARCHAR,stringTypeHandler);
    }

    private void registerTagResolvers() {
        tagResolvers.put("if",new IFTagResolver());
        tagResolvers.put("where",new WhereTagResolver());
    }

    public TagResolver getTagResolver(String tagName){
        TagResolver tagResolver = tagResolvers.get(tagName);
        Assert.notNull(tagResolver);
        return tagResolver;
    }


}
