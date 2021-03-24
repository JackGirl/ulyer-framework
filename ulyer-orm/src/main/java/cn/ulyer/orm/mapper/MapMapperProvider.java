package cn.ulyer.orm.mapper;

import cn.ulyer.orm.config.OrmConfiguration;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class MapMapperProvider  implements MapperProvider{

    List<MapperScanner> scanners ;

    Map<String,MapperDefinition> mappers = new ConcurrentHashMap<>(100);

    public MapMapperProvider(OrmConfiguration ormConfiguration,List<MapperScanner> scanners){
        this.setScanners(scanners);
        for (MapperScanner scanner : scanners) {
            mappers.putAll(scanner.scanner(ormConfiguration));
        }
    }



    @Override
    public MapperMethod getMapperMethod(String namespace,String id) {
       return null;
    }

    @Override
    public MapperDefinition getMapperDefinition(String namespace) {
        return mappers.get(namespace);
    }
}
