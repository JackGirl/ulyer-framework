package com.test.mybatis.factory;

import com.test.mybatis.result.ResultTypeHandler;
import lombok.Data;

import java.util.List;

@Data
public class OrmConfiguration {

    private String [] mapperLocaltions;

    private String [] basePackages;

    private boolean mapperUnderScore = false;

    public List<String> typeHandlerClasses;

    private List<String> pluginClasses;
}
