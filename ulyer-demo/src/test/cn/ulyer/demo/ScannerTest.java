package cn.ulyer.demo;

import cn.ulyer.orm.config.OrmConfiguration;
import cn.ulyer.orm.mapper.MapperDefinition;
import cn.ulyer.orm.mapper.MapperScanner;
import cn.ulyer.orm.mapper.SimpleMapperScanner;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.Map;


public class ScannerTest {


    @Test
    public void testScanner() throws Exception {
        OrmConfiguration ormConfiguration = new OrmConfiguration();
        ormConfiguration.setBasePackages("cn.ulyer.demo.dao");
        MapperScanner scanner = new SimpleMapperScanner();
        Map<String, MapperDefinition> mapeprs = scanner.scanner(ormConfiguration);
        System.out.println(JSON.toJSONString(mapeprs,true));
    }

}
