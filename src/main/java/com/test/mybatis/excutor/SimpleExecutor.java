package com.test.mybatis.excutor;

import com.test.mybatis.config.OrmConfiguration;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: yeqi
 * @Description:
 * @Date: Create in 21:03 2021/3/20
 */

@Getter
@Setter
public class SimpleExecutor extends AbstractExecutor {


    public SimpleExecutor (OrmConfiguration ormConfiguration){
        super(ormConfiguration);
    }



}
