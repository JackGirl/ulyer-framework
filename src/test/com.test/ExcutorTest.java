package com.test;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSON;
import com.test.mybatis.mapper.Mapper;
import com.test.mybatis.enums.MapperSqlType;
import com.test.mybatis.excutor.SimpleExecutor;
import com.test.mybatis.parameter.RegexParameterResolver;
import org.junit.Test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: yeqi
 * @Description:
 * @Date: Create in 21:57 2021/3/20
 */
public class ExcutorTest {


    @Test
    public void testExcutor() throws SQLException {
        SimpleExecutor excutor = new SimpleExecutor();
        excutor.setParameterResolver(new RegexParameterResolver());
        Mapper mapper = new Mapper();
        mapper.setMapperSqlType(MapperSqlType.SELECT);
        mapper.setSql("select * from test_2 where id = {id}");
        Map<String,Object> params = MapUtil.createMap(HashMap.class);
        params.put("id","1");
        Object object = excutor.execute(mapper,params);
        System.out.println(JSON.toJSONString(object));
    }

    @Test
    public void testExcutorInsert() throws SQLException {
        SimpleExecutor excutor = new SimpleExecutor();
        excutor.setParameterResolver(new RegexParameterResolver());
        Mapper mapper = new Mapper();
        mapper.setMapperSqlType(MapperSqlType.INSERT);
        mapper.setSql("insert into user values({id},{name})");
        Map<String,Object> params = MapUtil.createMap(HashMap.class);
        params.put("id","4");
        params.put("name","test");
        Object object = excutor.execute(mapper,params);
        System.out.println(JSON.toJSONString(object));
    }


    @Test
    public void testExcutorUpdate() throws SQLException {
        SimpleExecutor excutor = new SimpleExecutor();
        excutor.setParameterResolver(new RegexParameterResolver());
        Mapper mapper = new Mapper();
        mapper.setMapperSqlType(MapperSqlType.UPDATE);
        mapper.setSql("update  user set name = {name} where id = {id}");
        Map<String,Object> params = MapUtil.createMap(HashMap.class);
        params.put("id","3");
        params.put("name","ttttt");
        Object object = excutor.execute(mapper,params);
        System.out.println(JSON.toJSONString(object));
    }

    @Test
    public void testExcutorDel() throws SQLException {
        SimpleExecutor excutor = new SimpleExecutor();
        excutor.setParameterResolver(new RegexParameterResolver());
        Mapper mapper = new Mapper();
        mapper.setMapperSqlType(MapperSqlType.DELETE);
        mapper.setSql("delete from user where id ={id}");
        Map<String,Object> params = MapUtil.createMap(HashMap.class);
        params.put("id","3");
        Object object = excutor.execute(mapper,params);
        System.out.println(JSON.toJSONString(object));
    }


}
