package cn.ulyer.orm.excutor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface SqlSession {

    <T> T execute(String namespace,Object... params);

    <E> List<E> selectList(String namespace, Object ...params);

    <T> T selectOne(String namespace ,Object ...params) throws SQLException;

    int update(String namespace,  Object ...params);

    int insert(String namespace, Object ...params);

    int delete(String namespace,  Object ...params);

    Connection getConnection();
}
