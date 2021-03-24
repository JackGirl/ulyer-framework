package cn.ulyer.orm.excutor;

import cn.ulyer.orm.mapper.MapperMethod;

import java.util.Map;

/**
 * @Author: yeqi
 * @Description:
 * @Date: Create in 21:01 2021/3/20
 */
public interface Executor {


    <T> T execute(MapperMethod mapperMethod, Map<String,Object> params) ;





}
