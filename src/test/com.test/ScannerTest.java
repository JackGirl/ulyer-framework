package com.test;

import com.test.mybatis.config.OrmConfiguration;
import com.test.mybatis.mapper.DefaultMapperScanner;
import org.junit.Test;


public class ScannerTest {


    @Test
    public void testScanner() throws Exception {

        OrmConfiguration ormConfiguration = new OrmConfiguration();
        ormConfiguration.setBasePackages("com.test.mybatis");
        DefaultMapperScanner scanner = new DefaultMapperScanner();
       scanner.scanner(ormConfiguration);

    }

}
