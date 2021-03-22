package com.test.mybatis.factory;

import com.test.mybatis.excutor.Executor;
import com.test.mybatis.excutor.SimpleExecutor;
import com.test.mybatis.mapper.DefaultMapperScanner;
import com.test.mybatis.mapper.MapperDefinition;
import com.test.mybatis.mapper.MapperScanner;
import lombok.Data;

import java.util.Map;

/**
 * @Author: yeqi
 * @Description:
 * @Date: Create in 20:53 2021/3/20
 */
@Data
public class MybatisManageFactory implements OrmFactory{

    private MapperScanner mapperScanner;

    private Executor executor ;


    public MybatisManageFactory(OrmConfiguration ormConfiguration){
        this.mapperScanner = new DefaultMapperScanner();
        this.executor = new SimpleExecutor(ormConfiguration);
    }



}
