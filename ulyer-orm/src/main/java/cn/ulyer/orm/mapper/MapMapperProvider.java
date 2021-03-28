package cn.ulyer.orm.mapper;

import cn.hutool.core.lang.Assert;
import cn.ulyer.orm.config.OrmConfiguration;
import cn.ulyer.orm.mapper.parameter.ParameterObject;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class MapMapperProvider  implements MapperProvider{

    List<MapperScanner> scanners  = new ArrayList<>();

    Map<String,MapperDefinition> mappers = new ConcurrentHashMap<>(100);

    public MapMapperProvider(OrmConfiguration ormConfiguration){
        scanners.add(new SimpleMapperScanner());
        this.setScanners(scanners);
        for (MapperScanner scanner : scanners) {
            mappers.putAll(scanner.scanner(ormConfiguration));
        }
    }



    @Override
    public MapperMethod getMapperMethod(String namespace) {
       MapperDefinition mapperDefinition = getMapperDefinition(namespace);
       Assert.notNull(mapperDefinition);
       return mapperDefinition.getMapperMethodMap().get(namespace);
    }

    @Override
    public MapperDefinition getMapperDefinition(String namespace) {
        return mappers.get(namespace);
    }

    @Override
    public MapperWrapper getMapperWrapper(String namespace,Object...params) {
        MapperDefinition mapperDefinition = getMapperDefinition(getMapperNameSpace(namespace));
        Assert.notNull(mapperDefinition,"no mapperDefinition for space："+namespace);
        MapperMethod mapperMethod = mapperDefinition.getMapperMethodMap().get(namespace);
        Assert.notNull(mapperMethod,"no sqlId for namespace :"+namespace);
        MapperWrapper mapperWrapper = new MapperWrapper();
        mapperWrapper.setBoundSql(mapperMethod.getSql());
        mapperWrapper.setMapperMethod(mapperMethod);
        mapperWrapper.setMapperDefinition(mapperDefinition);
        mapperWrapper.setParameterObject(ParameterObject.newParameter(mapperMethod,params));
        return mapperWrapper;
    }

    private String getMapperNameSpace(String space){
        return space.substring(0,space.lastIndexOf("."));
    }

    private String getMapperMethodId(String space){
        return space.substring(space.lastIndexOf(".")+1);
    }
}
