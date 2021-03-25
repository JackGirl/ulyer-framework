package cn.ulyer.orm.config;

import cn.ulyer.orm.plugin.OrmInterceptor;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class OrmConfiguration {

    private String [] mapperLocations;

    private String [] basePackages;

    private boolean mapperUnderScore = false;

    public Set<String> typeHandlerClasses;

    private Set<String> pluginClasses;

    private List<OrmInterceptor> ormInterceptors;

    public void setBasePackages(String ... packages){
        this.basePackages = packages;
    }

    public void setMapperLocations(String ... mapperLocations){
        this.mapperLocations = mapperLocations;
    }
}
