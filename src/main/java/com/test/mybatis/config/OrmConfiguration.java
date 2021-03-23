package com.test.mybatis.config;

import com.test.mybatis.plugin.OrmPlugin;
import com.test.mybatis.result.ResultTypeHandler;
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
