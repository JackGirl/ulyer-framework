package com.test;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSON;
import com.test.mybatis.MapperSql;
import com.test.mybatis.enums.MapperSqlType;
import com.test.mybatis.excutor.SimpleExcutor;
import com.test.mybatis.parameter.RegexParameterResolver;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: yeqi
 * @Description:
 * @Date: Create in 21:57 2021/3/20
 */
public class ExcutorTest {


    @Test
    public void testExcutor(){
        SimpleExcutor excutor = new SimpleExcutor();
        excutor.setParameterResolver(new RegexParameterResolver());
        MapperSql mapperSql = new MapperSql();
        mapperSql.setMapperSqlType(MapperSqlType.SELECT);
        mapperSql.setSql("select * from test_2 where id = {id}");
        Map<String,Object> params = MapUtil.createMap(HashMap.class);
        params.put("id","1");
        Object object = excutor.excutor(mapperSql,params);
        System.out.println(JSON.toJSONString(object));
    }

    @Test
    public void testExcutorInsert(){
        SimpleExcutor excutor = new SimpleExcutor();
        excutor.setParameterResolver(new RegexParameterResolver());
        MapperSql mapperSql = new MapperSql();
        mapperSql.setMapperSqlType(MapperSqlType.INSERT);
        mapperSql.setSql("insert into user values({id},{name})");
        Map<String,Object> params = MapUtil.createMap(HashMap.class);
        params.put("id","4");
        params.put("name","test");
        Object object = excutor.excutor(mapperSql,params);
        System.out.println(JSON.toJSONString(object));
    }


    @Test
    public void testExcutorUpdate(){
        SimpleExcutor excutor = new SimpleExcutor();
        excutor.setParameterResolver(new RegexParameterResolver());
        MapperSql mapperSql = new MapperSql();
        mapperSql.setMapperSqlType(MapperSqlType.UPDATE);
        mapperSql.setSql("update  user set name = {name} where id = {id}");
        Map<String,Object> params = MapUtil.createMap(HashMap.class);
        params.put("id","3");
        params.put("name","ttttt");
        Object object = excutor.excutor(mapperSql,params);
        System.out.println(JSON.toJSONString(object));
    }

    @Test
    public void testExcutorDel(){
        SimpleExcutor excutor = new SimpleExcutor();
        excutor.setParameterResolver(new RegexParameterResolver());
        MapperSql mapperSql = new MapperSql();
        mapperSql.setMapperSqlType(MapperSqlType.DELETE);
        mapperSql.setSql("delete from user where id ={id}");
        Map<String,Object> params = MapUtil.createMap(HashMap.class);
        params.put("id","3");
        Object object = excutor.excutor(mapperSql,params);
        System.out.println(JSON.toJSONString(object));
    }


}
