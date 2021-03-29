package cn.ulyer.orm.excutor;

import cn.ulyer.orm.config.OrmConfiguration;
import cn.ulyer.orm.mapper.MapperProvider;
import cn.ulyer.orm.mapper.MapperWrapper;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.util.List;

public class DefaultSqlSession implements SqlSession{


    private MapperProvider mapperProvider;

    private OrmConfiguration ormConfiguration;

    private Connection connection;

    private Executor executor;

    public DefaultSqlSession( MapperProvider mapperProvider,OrmConfiguration ormConfiguration,Connection connection){
        this.mapperProvider = mapperProvider;
        this.ormConfiguration = ormConfiguration;
        this.connection = connection;
        executor = new SimpleExecutor(connection,ormConfiguration);
    }


    /**
     * 真正的执行方法  包括执行周期也在这里
     * @param namespace
     * @param params
     * @param <T>
     * @return
     */
    @SneakyThrows
    @Override
    public <T> T execute(String namespace, Object... params) {
        MapperWrapper mapperWrapper = mapperProvider.getMapperWrapper(namespace,params);
        return executor.execute(mapperWrapper);
    }

    @Override
    public <E> List<E> selectList(String namespace,  Object ...params) {
        return executor.selectList(mapperWrapper(namespace,params));
    }

    @Override
    public <T> T selectOne(String namespace, Object... params) {
        return executor.selectOne(mapperWrapper(namespace,params));
    }

    @Override
    public int update(String namespace,  Object ...params) {
        return this.executor.update(mapperWrapper(namespace,params));
    }

    @Override
    public int insert(String namespace,  Object ...params) {
        return this.executor.insert(mapperWrapper(namespace,params));
    }

    @Override
    public int delete(String namespace, Object ...params) {
        return this.executor.delete(mapperWrapper(namespace,params));
    }

    @Override
    public Connection getConnection() {
        return connection;
    }


    private MapperWrapper mapperWrapper(String namespace,Object...params){
        return mapperProvider.getMapperWrapper(namespace,params);
    }




}
