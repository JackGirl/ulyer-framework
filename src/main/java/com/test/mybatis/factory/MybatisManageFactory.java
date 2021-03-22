package com.test.mybatis.factory;

import com.test.mybatis.excutor.Executor;
import com.test.mybatis.excutor.SimpleExecutor;
import com.test.mybatis.mapper.MapperDefinition;
import com.test.mybatis.mapper.Mapper;
import lombok.Data;

import java.util.Map;

/**
 * @Author: yeqi
 * @Description:
 * @Date: Create in 20:53 2021/3/20
 */
@Data
public class MybatisManageFactory {


    private Map<String , MapperDefinition> mappers;


    private Executor executor = new SimpleExecutor();

    public Object executeResult(Mapper mapper, Map<String,Object> params){
            return executor.execute(mapper,params);
    }

}
