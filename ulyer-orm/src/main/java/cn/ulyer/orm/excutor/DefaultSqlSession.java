package cn.ulyer.orm.excutor;

import cn.ulyer.orm.config.OrmConfiguration;
import cn.ulyer.orm.mapper.MapperProvider;
import cn.ulyer.orm.mapper.MapperWrapper;

import java.sql.Connection;
import java.util.Map;

public class DefaultSqlSession implements SqlSession{

    private Executor executor;

    private MapperProvider mapperProvider;

    private OrmConfiguration ormConfiguration;

    public DefaultSqlSession(Executor executor, MapperProvider mapperProvider,OrmConfiguration ormConfiguration){
        this.executor = executor;
        this.mapperProvider = mapperProvider;
        this.ormConfiguration = ormConfiguration;
    }


    @Override
    public <T> T execute(String namespace, Map<String, Object> params) {
        return null;
    }

    @Override
    public <T> T selectList(String namespace, Map<String,Object> params) {
        MapperWrapper mapperWrapper = mapperProvider.getMapperWrapper(namespace);

        return null;
    }

    @Override
    public int update(String namespace, Map<String,Object> params) {
        return 0;
    }

    @Override
    public int insert(String namespace, Map<String,Object> params) {
        return 0;
    }

    @Override
    public int delete(String namespace, Map<String,Object> params) {
        return 0;
    }

    @Override
    public Connection getConnection() {
        return null;
    }





}
