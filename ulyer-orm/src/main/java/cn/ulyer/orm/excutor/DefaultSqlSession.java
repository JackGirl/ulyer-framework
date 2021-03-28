package cn.ulyer.orm.excutor;

import cn.hutool.core.lang.Assert;
import cn.ulyer.orm.mapper.MapperDefinition;
import cn.ulyer.orm.mapper.MapperProvider;

import java.sql.Connection;
import java.util.Map;

public class DefaultSqlSession implements SqlSession{

    private Executor executor;

    private MapperProvider mapperProvider;

    public DefaultSqlSession(Executor executor, MapperProvider mapperProvider){
        this.executor = executor;
        this.mapperProvider = mapperProvider;
    }


    @Override
    public <T> T execute(String namespace, Map<String, Object> params) {
        return null;
    }

    @Override
    public <T> T selectList(String namespace, Map<String,Object> params) {
        MapperDefinition mapperDefinition = mapperProvider.getMapperDefinition(getMapperNameSpace(namespace));
        Assert.notNull(mapperDefinition,"no mapperDefinition for namespace :"+namespace);

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

    private String getMapperNameSpace(String space){
        return space.substring(0,space.lastIndexOf("."));
    }

    private String getMapperMethodId(String space){
        return space.substring(space.lastIndexOf(".")+1);
    }

}
