package cn.ulyer.orm.excutor;


import cn.ulyer.orm.mapper.MapperWrapper;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: yeqi
 * @Description:
 * @Date: Create in 21:01 2021/3/20
 */
public interface Executor {


    <E> List<E> selectList(MapperWrapper mapperWrapper) ;

    <T> T selectOne(MapperWrapper mapperWrapper) throws SQLException;

    int insert(MapperWrapper mapperWrapper);

    int update(MapperWrapper mapperWrapper);

    int delete(MapperWrapper mapperWrapper);

    <T> T execute(MapperWrapper mapperWrapper);
}
