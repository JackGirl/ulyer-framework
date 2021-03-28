package cn.ulyer.orm.mapper;

public interface MapperProvider {

    /**
     * 获取mapperMethod
     * @param namespace
     * @return
     */
    MapperMethod getMapperMethod(String namespace);

    /**
     * 获取mapper定义 按命名空间唯一键
     * @param namespace
     * @return
     */
    MapperDefinition getMapperDefinition(String namespace);


    MapperWrapper getMapperWrapper(String namespace,Object...params);
}
