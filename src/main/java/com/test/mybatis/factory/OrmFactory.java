package com.test.mybatis.factory;

import com.test.mybatis.config.OrmConfiguration;
import com.test.mybatis.excutor.SqlSession;

import javax.sql.DataSource;

public interface OrmFactory {

    void setDataSource(DataSource dataSource);

    DataSource getDataSource(DataSource dataSource);

    void setConfiguration(OrmConfiguration configuration);

    OrmConfiguration getConfiguration();

    SqlSession createSqlSession();

}
