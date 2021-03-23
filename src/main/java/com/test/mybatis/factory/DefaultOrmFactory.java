package com.test.mybatis.factory;

import com.test.mybatis.config.OrmConfiguration;
import com.test.mybatis.excutor.Executor;
import com.test.mybatis.excutor.SimpleExecutor;
import com.test.mybatis.excutor.SqlSession;
import com.test.mybatis.mapper.DefaultMapperScanner;
import com.test.mybatis.mapper.MapperProvider;
import com.test.mybatis.mapper.MapperScanner;
import lombok.Data;

import javax.sql.DataSource;

/**
 * @Author: yeqi
 * @Description:
 * @Date: Create in 20:53 2021/3/20
 */
@Data
public class DefaultOrmFactory implements OrmFactory{

    private MapperProvider mapperProvider;

    private Executor executor ;

    private DataSource dataSource;

    private OrmConfiguration ormConfiguration ;

    public DefaultOrmFactory(OrmConfiguration ormConfiguration){
        this.setConfiguration(ormConfiguration);
    }


    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public DataSource getDataSource(DataSource dataSource) {
        return dataSource;
    }

    @Override
    public void setConfiguration(OrmConfiguration configuration) {
        this.ormConfiguration = configuration;
    }

    @Override
    public OrmConfiguration getConfiguration() {
        return this.ormConfiguration;
    }

    @Override
    public SqlSession createSqlSession() {
        return null;
    }
}
