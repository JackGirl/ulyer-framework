package com.test.mybatis;

import com.test.mybatis.excutor.Excutor;
import com.test.mybatis.excutor.SimpleExcutor;
import lombok.Data;

import java.util.Map;

/**
 * @Author: yeqi
 * @Description:
 * @Date: Create in 20:53 2021/3/20
 */
@Data
public class MybatisManageFactory {


    private Map<String ,MapperDefinition> mappers;


    private Excutor excutor = new SimpleExcutor();

    public Object excutorResult(MapperSql mapperSql, Map<String,Object> params){
        return excutor.excutor(mapperSql,params);
    }

}
