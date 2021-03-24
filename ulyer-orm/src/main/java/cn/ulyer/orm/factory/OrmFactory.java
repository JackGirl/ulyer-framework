package cn.ulyer.orm.factory;

import cn.ulyer.orm.config.OrmConfiguration;
import cn.ulyer.orm.excutor.SqlSession;

import javax.sql.DataSource;

public interface OrmFactory {

    void setDataSource(DataSource dataSource);

    DataSource getDataSource(DataSource dataSource);

    void setConfiguration(OrmConfiguration configuration);

    OrmConfiguration getConfiguration();

    SqlSession createSqlSession();

}
