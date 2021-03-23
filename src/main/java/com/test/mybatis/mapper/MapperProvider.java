package com.test.mybatis.mapper;

public interface MapperProvider {

    /**
     * 获取mapperMethod
     * @param namespace
     * @param methodId
     * @return
     */
    MapperMethod getMapperMethod(String namespace,String methodId);

    /**
     * 获取mapper定义 按命名空间唯一键
     * @param namespace
     * @return
     */
    MapperDefinition getMapperDefinition(String namespace);

}
