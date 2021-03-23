package com.test.mybatis.mapper;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.test.mybatis.config.OrmConfiguration;
import com.test.mybatis.utils.LogUtils;
import com.test.mybatis.utils.ResourceUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class DefaultMapperScanner implements MapperScanner {


    @Override
    public MapperDefinition register(Class<?> mapperClass) {
        return null;
    }

    @Override
    public MapperDefinition register(InputStream stream) {
        return null;
    }


    @Override
    public Map<String, MapperDefinition> scanner(OrmConfiguration configuration) {
        Map<String, MapperDefinition> mappers = new HashMap<>();
        try {
            Set<Class<?>> jarClasses = new HashSet<>();
            String[] basePackages = configuration.getBasePackages();
            for (String basePackage : basePackages) {
                ResourceUtils.loadClassFromFile(basePackage,jarClasses);
                for (Class<?> jarClass : jarClasses) {
                    System.out.println(jarClass.getName());
                    mappers.put(jarClass.getName(), register(jarClass));
                }
            }
        } catch (Exception e) {
            LogUtils.error(e);
        }


        return mappers;
    }



}
