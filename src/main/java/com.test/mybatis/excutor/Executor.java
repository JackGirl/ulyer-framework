package com.test.mybatis.excutor;

import com.test.mybatis.mapper.Mapper;

import java.util.Map;

/**
 * @Author: yeqi
 * @Description:
 * @Date: Create in 21:01 2021/3/20
 */
public interface Executor {


    Object execute(Mapper mapper, Map<String,Object> params) ;


}
