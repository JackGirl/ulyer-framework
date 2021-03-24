package cn.ulyer.orm.factory;

import cn.ulyer.orm.config.OrmConfiguration;
import cn.ulyer.orm.excutor.Executor;
import cn.ulyer.orm.excutor.SqlSession;
import cn.ulyer.orm.mapper.MapperProvider;
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
