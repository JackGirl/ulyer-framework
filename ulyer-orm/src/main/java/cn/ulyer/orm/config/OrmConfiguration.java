package cn.ulyer.orm.config;

import cn.ulyer.orm.plugin.OrmPlugin;
import cn.ulyer.orm.result.ResultTypeHandler;
import lombok.Data;

import java.util.Set;

@Data
public class OrmConfiguration {

    private String [] mapperLocations;

    private String [] basePackages;

    private boolean mapperUnderScore = false;

    public Set<Class<? extends ResultTypeHandler>> typeHandlerClasses;

    private Set<Class<? extends OrmPlugin>> pluginClasses;

    public void setBasePackages(String ... packages){
        this.basePackages = packages;
    }

    public void setMapperLocations(String ... mapperLocations){
        this.mapperLocations = mapperLocations;
    }
}
