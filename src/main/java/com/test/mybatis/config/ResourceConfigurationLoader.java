package com.test.mybatis.config;

import java.io.InputStream;
import java.util.Properties;

public class ResourceConfigurationLoader {


    public static OrmConfiguration loadConfiguration(String filePaht){
        return new OrmConfiguration();
    }

    public static OrmConfiguration loadConfigration(InputStream inputStream){
        return new OrmConfiguration();
    }

    public static OrmConfiguration loadConfiguration(Properties properties){
        return new OrmConfiguration();
    }

}
