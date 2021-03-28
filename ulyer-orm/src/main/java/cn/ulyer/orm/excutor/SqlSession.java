package cn.ulyer.orm.excutor;

import java.sql.Connection;
import java.util.Map;

public interface SqlSession {

    <T> T execute(String namespace,Map<String,Object> params);

    <T> T selectList(String namespace, Map<String,Object> params);

    int update(String namespace, Map<String,Object> params);

    int insert(String namespace, Map<String,Object> params);

    int delete(String namespace, Map<String,Object> params);

    Connection getConnection();
}
