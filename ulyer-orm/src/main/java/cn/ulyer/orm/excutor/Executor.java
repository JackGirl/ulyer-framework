package cn.ulyer.orm.excutor;



import cn.ulyer.orm.mapper.MapperWrapper;

import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * @Author: yeqi
 * @Description:
 * @Date: Create in 21:01 2021/3/20
 */
public interface Executor {


    <T> T execute(PreparedStatement statement,MapperWrapper mapperWrapper) ;


}
