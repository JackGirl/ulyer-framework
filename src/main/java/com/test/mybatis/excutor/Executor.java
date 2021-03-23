package com.test.mybatis.excutor;

import com.test.mybatis.mapper.MapperMethod;

import java.util.Map;

/**
 * @Author: yeqi
 * @Description:
 * @Date: Create in 21:01 2021/3/20
 */
public interface Executor {


    <T> T execute(MapperMethod mapperMethod, Map<String,Object> params) ;





}
