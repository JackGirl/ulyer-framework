package com.test.mybatis.excutor;

import java.sql.Connection;

public interface SqlSession {

    <T> T selectList();

    int update();

    int insert();

    int delete();

    Connection getConnection();
}
