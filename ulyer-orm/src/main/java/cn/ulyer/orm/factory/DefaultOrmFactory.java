package cn.ulyer.orm.factory;

import cn.ulyer.orm.config.OrmConfiguration;
import cn.ulyer.orm.excutor.DefaultSqlSession;
import cn.ulyer.orm.excutor.Executor;
import cn.ulyer.orm.excutor.SimpleExecutor;
import cn.ulyer.orm.excutor.SqlSession;
import cn.ulyer.orm.mapper.MapMapperProvider;
import cn.ulyer.orm.mapper.MapperProvider;
import lombok.Data;
import lombok.SneakyThrows;

import javax.sql.DataSource;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.SQLException;

/**
 * @Author: yeqi
 * @Description:
 * @Date: Create in 20:53 2021/3/20
 */
@Data
public class DefaultOrmFactory implements OrmFactory{

    private MapperProvider mapperProvider ;

    private Executor executor ;

    private DataSource dataSource;

    private OrmConfiguration ormConfiguration ;

    public DefaultOrmFactory(OrmConfiguration ormConfiguration){
        this.setConfiguration(ormConfiguration);
        this.executor = new SimpleExecutor(ormConfiguration);
        this.mapperProvider = new MapMapperProvider(ormConfiguration);
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
    @SneakyThrows
    public SqlSession createSqlSession() {
        return new DefaultSqlSession(executor,mapperProvider,ormConfiguration,dataSource.getConnection());
    }

    @Override
    public <T> T getMapper(Class<T> mapper) {
        return (T) MapperInvocationHandler.instance(this,mapper);
    }






}
