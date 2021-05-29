package cn.ulyer.orm.mapper;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.ulyer.orm.config.OrmConfiguration;
import cn.ulyer.orm.utils.LogUtils;
import cn.ulyer.orm.utils.ResourceUtils;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public abstract class AbstractBaseMapperScanner implements MapperScanner {


    public AbstractBaseMapperScanner(){
    }

    @Override
    public abstract MapperDefinition register(Class<?> mapperClass) ;

    @Override
    public abstract MapperDefinition register(InputStream stream) throws Exception;


    @SneakyThrows
    @Override
    public Map<String, MapperDefinition> scanner(OrmConfiguration configuration) {
        Map<String, MapperDefinition> mappers = new HashMap<>(120);
        try {
            Set<Class<?>> jarClasses = new HashSet<>();
            String[] basePackages = configuration.getBasePackages();
            for (String basePackage : basePackages) {
                ResourceUtils.loadClassFromFile(basePackage,jarClasses);
                for (Class<?> jarClass : jarClasses) {
                    if(jarClass.isInterface()){
                        mappers.put(jarClass.getName(), register(jarClass));
                    }
                }
            }
        } catch (Exception e) {
            LogUtils.error(e);
        }
        for (String location : configuration.getMapperLocations()){
            List<InputStream> xmlFiles = ResourceUtils.loadInputStreamFromURL(location,"xml");
            if(CollectionUtil.isNotEmpty(xmlFiles)){
                for (InputStream xmlFile : xmlFiles) {
                    MapperDefinition mapperDefinition  = register(xmlFile);
                    registerMapper(mapperDefinition,mappers);
                }
            }
        }
        return mappers;
    }


    protected void registerMapper(final MapperDefinition definition, Map<String,MapperDefinition> mappers){
        MapperDefinition mapper = mappers.get(definition.getNamespace());
        if(null!=mapper){
            /**
             * 不存在的先添加
             */
            definition.getMapperMethodMap().forEach((k,v)->{
                if(!mapper.getMapperMethodMap().containsKey(k)){
                    mapper.getMapperMethodMap().put(k,v);
                }else{
                    MapperMethod mapperMethod = mapper.getMapperMethodMap().get(k);
                    if(StrUtil.isBlank(mapperMethod.getXml())){
                        mapperMethod.setXml(v.getXml());
                    }
                    mapperMethod.setMapperSqlType(v.getMapperSqlType());
                }
            });
            return;
        }
        mappers.put(definition.getNamespace(),definition);
    }


}
