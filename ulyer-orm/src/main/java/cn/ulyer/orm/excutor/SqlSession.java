package cn.ulyer.orm.excutor;

import java.sql.Connection;
import java.util.Map;

public interface SqlSession {

    <T> T execute(String namespace,Object[] params);

    <T> T selectList(String namespace, Object ...params);

    int update(String namespace,  Object ...params);

    int insert(String namespace, Object ...params);

    int delete(String namespace,  Object ...params);

    Connection getConnection();
}
