package cn.ulyer.orm.factory;

import cn.ulyer.orm.config.OrmConfiguration;
import cn.ulyer.orm.excutor.Executor;
import cn.ulyer.orm.excutor.SqlSession;

import javax.sql.DataSource;
import java.sql.SQLException;

public interface OrmFactory {

    void setDataSource(DataSource dataSource);

    DataSource getDataSource(DataSource dataSource);

    void setConfiguration(OrmConfiguration configuration);

    OrmConfiguration getConfiguration();

    SqlSession createSqlSession() ;

    <T> T getMapper(Class<T> mapper);

}
