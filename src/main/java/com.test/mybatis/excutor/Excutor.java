package com.test.mybatis.excutor;

import com.test.mybatis.MapperSql;

import java.util.Map;

/**
 * @Author: yeqi
 * @Description:
 * @Date: Create in 21:01 2021/3/20
 */
public interface Excutor {


    Object excutor(MapperSql mapperSql, Map<String,Object> params);


}
