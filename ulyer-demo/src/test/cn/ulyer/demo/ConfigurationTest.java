package cn.ulyer.demo;

import cn.ulyer.orm.config.OrmConfiguration;
import cn.ulyer.orm.config.ResourceConfigurationLoader;
import cn.ulyer.orm.plugin.PageInterceptor;
import org.junit.Test;

import java.io.FileNotFoundException;

public class ConfigurationTest {

    @Test
    public void test() throws FileNotFoundException {
        OrmConfiguration ormConfiguration = ResourceConfigurationLoader.loadConfiguration("orm.yml");
        System.out.println(ormConfiguration);
    }

}
