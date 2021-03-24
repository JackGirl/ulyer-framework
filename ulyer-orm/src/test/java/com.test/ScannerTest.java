package com.test;

import cn.ulyer.orm.config.OrmConfiguration;
import cn.ulyer.orm.mapper.MapperScanner;
import cn.ulyer.orm.mapper.SimpleMapperScanner;
import org.junit.Test;


public class ScannerTest {


    @Test
    public void testScanner() throws Exception {

        OrmConfiguration ormConfiguration = new OrmConfiguration();
        ormConfiguration.setBasePackages("com.test.mybatis");
        MapperScanner scanner = new SimpleMapperScanner();
        scanner.scanner(ormConfiguration);

    }

}
