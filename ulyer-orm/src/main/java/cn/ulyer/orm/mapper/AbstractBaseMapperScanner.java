package cn.ulyer.orm.mapper;


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
    public abstract MapperDefinition register(InputStream stream);


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
                    mappers.put(jarClass.getName(), register(jarClass));
                }
            }
        } catch (Exception e) {
            LogUtils.error(e);
        }
/*        for (String location : configuration.getMapperLocations()){
            File mapperLocationDir = new File(location);
            List<File> xmlFiles = ResourceUtils.loadFileFromDir(mapperLocationDir);
            for (File xmlFile : xmlFiles) {
                MapperDefinition mapperDefinition  = register(new FileInputStream(xmlFile));
                registerMapper(mapperDefinition,mappers);
            }
        }*/
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
                    if(StrUtil.isBlank(mapperMethod.getSql())){
                        mapperMethod.setSql(v.getSql());
                    }
                }
            });
            return;
        }
        mappers.put(definition.getNamespace(),definition);
    }


}
